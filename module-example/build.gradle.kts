import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.10"
    kotlin("kapt") version "1.3.31"
}

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compileOnly("io.knotx:knotx-core:1.5.0")
    compileOnly("io.knotx:knotx-template-engine-api:1.5.0")
    compileOnly("io.knotx:knotx-template-engine-handlebars:1.5.0")
    compileOnly("io.knotx:knotx-databridge-api:1.5.0")
    compileOnly("io.knotx:knotx-forms-api:1.5.0")
    compileOnly("io.vertx:vertx-core:3.5.4")
    compileOnly("io.vertx:vertx-web-client:3.5.4")
    compileOnly("io.vertx:vertx-web:3.5.4")
    kapt("io.vertx:vertx-codegen:3.5.4:processor")
    compileOnly("io.vertx:vertx-codegen:3.5.4")
    compileOnly("io.vertx:vertx-rx-java2:3.5.4")
    compileOnly("io.vertx:vertx-service-proxy:3.5.4")

}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}