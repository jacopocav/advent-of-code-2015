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
    implementation(project("common"))
}