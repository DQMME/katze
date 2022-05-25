import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.google.devtools.ksp") version "1.6.21-1.0.5"
    kotlin("jvm") version "1.6.21"
    id("dev.schlaubi.mikbot.gradle-plugin") version "2.3.2"
}

group = "de.dqmme"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    // this one is included in the bot itself, therefore we make it compileOnly
    compileOnly(kotlin("stdlib-jdk8"))
    mikbot("dev.schlaubi", "mikbot-api", "3.2.0-SNAPSHOT")
    ksp("dev.schlaubi", "mikbot-plugin-processor", "2.2.0")
}

mikbotPlugin {
    description.set("This is a cool plugin with cats!")
    provider.set("DQMME")
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