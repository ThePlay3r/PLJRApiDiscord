import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("maven-publish")
}

group = "me.pljr"
version = "1.0"

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "me.pljr"
            artifactId = "pljrapidiscord"
            version = "1.0"

            from(components["java"])
        }
    }
}

repositories {
    mavenCentral()
    maven("https://m2.dv8tion.net/releases")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(group = "net.dv8tion", name = "JDA", version = "4.3.0_279")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}