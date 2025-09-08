val kotlin_version: String by project
val logback_version: String by project
val koin_version: String by project
val ktor_version: String by project

plugins {
    kotlin("jvm") version "2.2.10"
    id("io.ktor.plugin") version "3.2.3"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.10"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"

}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")

    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-server-call-logging:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")
    implementation("io.ktor:ktor-server-status-pages:${ktor_version}")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-default-headers:$ktor_version")
    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}