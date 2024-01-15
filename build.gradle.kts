plugins {
    kotlin("jvm") version "1.9.21"
    `maven-publish`
}

group = "com.niloda.arrow"
version = "0.0.4"

repositories {
    mavenCentral()
}

dependencies {
    val arrowVersion: String by project
    api("io.arrow-kt:arrow-core:$arrowVersion")
    implementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.junit.jupiter:junit-jupiter-params:5.10.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}
sourceSets {
    val main by getting
    val test by getting
    val testlib by creating {
        compileClasspath += main.output
        runtimeClasspath += main.output
    }
    val testlibtest by creating {
        compileClasspath += main.output
        runtimeClasspath += main.output
        compileClasspath += testlib.output
        runtimeClasspath += testlib.output
    }
}
val testlibImplementation by configurations
    .getting { extendsFrom(configurations.implementation.get()) }
val testlibtestImplementation by configurations
    .getting {
        extendsFrom(
            configurations.testImplementation.get(),
        )
    }

val testlib by configurations.creating {
    extendsFrom(configurations.implementation.get())
}

val testlibRuntimeOnly by configurations.getting
val testlibtestRuntimeOnly by configurations.getting

configurations["testlibRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

java {
    registerFeature("testlib") {
        usingSourceSet(sourceSets["testlib"])
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

