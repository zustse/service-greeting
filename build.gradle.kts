plugins {
    java
    application
    id("org.sonarqube") version "3.1.1"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.7.1"))

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
