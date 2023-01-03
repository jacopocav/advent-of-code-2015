plugins {
    kotlin("jvm") version "1.8.0"
}

allprojects {
    group = "ceejay.advent"
    version = "1.0-SNAPSHOT"

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
    subprojects.map { implementation(it) }
}