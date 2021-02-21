import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.Properties
import java.net.URI
import java.net.http.*
import java.net.http.HttpResponse.BodyHandlers
import java.net.http.HttpRequest
import java.util.concurrent.*
import java.util.concurrent.TimeUnit
import java.nio.charset.StandardCharsets.UTF_8

plugins {
    java
    application
    id("org.sonarqube") version "3.1.1"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:2.4.3"))
    testImplementation(platform("org.junit:junit-bom:5.7.1"))

    implementation("org.springframework.boot", "spring-boot-starter")
    testImplementation("org.junit.jupiter", "junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")
}

application {
    mainClass.set("my.App")
}

sonarqube {
    properties {
        property("sonar.projectKey", "stse_greeting")
        property("sonar.organization", "stse-1")
    }
}

tasks {
    test {
        useJUnitPlatform()
    }
    this.sonarqube {
        doLast {
            val props = Properties().also { props ->
                FileInputStream("build/sonar/report-task.txt").use {
                    props.load(it)
                }
            }

            logger.info(props.getProperty("ceTaskUrl"))

            val client = HttpClient.newBuilder().build()

            val request = HttpRequest.newBuilder()
                .uri(URI(props.getProperty("ceTaskUrl")))
                .version(HttpClient.Version.HTTP_1_1)
                .header("Authorization", "Basic YjhlY2Q4YzE1ZDYzMDFjNjk3ZWU4ZDhiMTdkMTJlYTI0YWU3MGMzODo=")
                .GET()
                .build()

            val executor = Executors.newSingleThreadScheduledExecutor()
            val poll = Callable {
                client.send(request, BodyHandlers.ofString()).body()!!.also { logger.info(it) }
            }

            do {
                val body: String = executor.schedule(poll, 1, TimeUnit.SECONDS).get()
            } while (!body.contains("\"status\":\"SUCCESS\""))

            FileOutputStream("build/sonar/gl-code-quality-report.json").writer(UTF_8).use {
                it.append(
                    """
                    [
                      {
                        "description": "'unused' is assigned a value but never used.",
                        "fingerprint": "7815696ecbf1c96e6894b779456d330e",
                        "severity": "minor",
                        "location": {
                          "path": "lib/index.js",
                          "lines": {
                            "begin": 42
                          }
                        }
                      }
                    ]
                    """.trimIndent()
                )
            }
        }
    }


}
