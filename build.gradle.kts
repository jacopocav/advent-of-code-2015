import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

plugins {
    kotlin("jvm") version "1.8.0"
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

allprojects {
    group = "ceejay.advent"

    apply(plugin = "org.jetbrains.kotlin.platform.jvm")

    kotlin {
        jvmToolchain(17)
    }

    tasks {
        test {
            useJUnitPlatform()
        }
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation(kotlin("test"))
    }
}

dependencies {
    runtimeOnly("ch.qos.logback:logback-classic:1.4.5")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    subprojects.map { implementation(it) }
}

application {
    applicationName = "Advent of Code 2015"
    archivesName.set("aoc2015")
    mainClass.set("MainKt")
}