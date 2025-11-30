@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    // Declara as versões dos plugins para os módulos
    plugins {
        id("com.android.application") version "8.13.1"
        kotlin("android") version "1.9.10"
        kotlin("kapt") version "1.9.10"
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Vinheria"
include(":app")
