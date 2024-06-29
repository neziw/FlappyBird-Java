plugins {
    id("java")
    id("checkstyle")
}

group = "ovh.neziw"
version = "1.0.0"

tasks.withType<JavaCompile> {
    options.compilerArgs = listOf("-Xlint:deprecation")
    options.encoding = "UTF-8"
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "ovh.neziw.flappybird.FlappyBirdGame"
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

checkstyle {
    toolVersion = "10.17.0"
    maxWarnings = 0
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    mavenLocal()
}