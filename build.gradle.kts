plugins {
    java
    application
    id("org.sonarqube") version "3.1.1"
    id("com.github.hierynomus.license") version "0.15.0"
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
}
