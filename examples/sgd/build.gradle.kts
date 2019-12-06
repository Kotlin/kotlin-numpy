import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.3.60"
}

application {
    mainClassName = "SGDRegressionKt"
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains:kotlin-numpy:0.1.0")
}

repositories {
    jcenter()
    maven("https://dl.bintray.com/kotlin/kotlin-numpy")
}

val compileKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions.jvmTarget = "1.8"