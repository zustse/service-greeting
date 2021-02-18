plugins {
    java
    application
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

tasks {
    test {
        useJUnitPlatform()
    }
}
