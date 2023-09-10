pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        val agpVersion = extra["agp.version"] as String
        id("com.android.library").version(agpVersion).apply(false)
        kotlin("multiplatform").version(kotlinVersion).apply(false)
        kotlin("native.cocoapods").version(kotlinVersion).apply(false)
    }
}

rootProject.name = "file_meassurer"