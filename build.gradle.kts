import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("maven-publish")
}

group = "com.spookybrain"
version = "0.3.0"

repositories {
    mavenCentral()
    google()
}

kotlin {

//    jvm("desktop")

    android("android") {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        publishAllLibraryVariants()
    }


    iosX64()
    iosArm64()
    iosSimulatorArm64()


    cocoapods {

        version = "0.1.0"
        summary = "Some description for a Kotlin/Native module"
        description = "This library provides a multiplatform remote file file reader"
        license = "{ :type => 'MIT', :text => 'LICENSE'}"
        homepage = "https://github.com/alextamariz616/file_measurer_lib"
        source = "{ :git => 'https://github.com/alextamariz616/file_measurer_lib.git', :tag => '$version' }"
        authors = "alexis.tamher@gmail.com"

        specRepos {
            url("https://github.com/alextamariz616/file_measurer_lib.git")
        }
        ios.deploymentTarget = "11.0"


        name = "FileMeasurer"

        xcodeConfigurationToNativeBuildType["CUSTOM_RELEASE"] = NativeBuildType.RELEASE

        framework {
            baseName = "FileMeasurer"
            isStatic = true
        }
    }

    
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
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

        }
//        val desktopMain by getting {
//            dependencies {
//                implementation("io.ktor:ktor-client-apache5:$ktorVersion")
//            }
//        }
    }
}


android {
    namespace = "com.spookybrain.kmm"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                artifactId = "file_measurer-android"
                groupId = "com.github.spookybrain"
            }
        }
    }
}