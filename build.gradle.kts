plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("maven-publish")
}

group = "com.spookybrain"
version = "0.2.0"

repositories {
    mavenCentral()
    google()
}

kotlin {
    jvm("desktop")

    android("android") {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        publishLibraryVariants("release")
    }

    ios()

    
    sourceSets {
        val ktorVersion = "2.3.0"

        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0")
            }
            dependsOn(commonMain)
        }
        val iosMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-apache5:$ktorVersion")
//                implementation("io.ktor:ktor-client-jvm:$ktorVersion")
            }
        }
    }
}


android {
    namespace = "com.spookybrain.kmm"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}