import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.google.devtools.ksp") version "1.6.21-1.0.5" // used for plugin-processor
    kotlin("jvm") version "1.6.21"
    id("dev.schlaubi.mikbot.gradle-plugin") version "2.2.0"
}

group = "de.dqmme"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    // this one is included in the bot itself, therefore we make it compileOnly
    // Or use: 'kotlin.stdlib.default.dependency=false' in gradle.properties
    compileOnly(kotlin("stdlib-jdk8"))
    mikbot("dev.schlaubi", "mikbot-api", "3.0.0-SNAPSHOT")
    ksp("dev.schlaubi", "mikbot-plugin-processor", "2.2.0")
}

mikbotPlugin {
    description.set("This is a cool plugin!")
    provider.set("Schlaubi")
    license.set("MIT")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

pluginPublishing {
    repositoryUrl.set("https://katze.streamerflash.de")
    targetDirectory.set(rootProject.file("ci-repo").toPath())
    projectUrl.set("https://github.com/DQMME/nothing")
}