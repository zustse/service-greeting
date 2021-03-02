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
    property("sonar.projectKey", "stse_service-greeting")
    property("sonar.organization", "zustse")
    property("sonar.host.url", "https://sonarcloud.io")
  }
}


tasks {
    test {
        useJUnitPlatform()
    }
}
