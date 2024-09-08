plugins {
    kotlin("jvm") version "1.9.23"
    id("io.qameta.allure") version "2.12.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

dependencies {

    implementation("org.slf4j:slf4j-simple:2.0.7")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:2.0.20")
    testImplementation("io.rest-assured:rest-assured:5.5.0")
    testImplementation("io.qameta.allure:allure-junit5:2.29.0")
    implementation("io.qameta.allure:allure-rest-assured:2.29.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    testImplementation("com.google.code.gson:gson:2.10.1")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}