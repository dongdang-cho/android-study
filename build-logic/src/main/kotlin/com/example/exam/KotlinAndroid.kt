package com.example.exam

import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


internal fun Project.configureKotlinAndroid() {

    pluginManager.apply("org.jetbrains.kotlin.android")


    androidExtension.apply {

        compileSdk = 33


        defaultConfig {
            minSdk = 27

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }

    }

    configureKotlin()

    val libs = extensions.libs


    dependencies {
        add("implementation", libs.findLibrary("androidx.core").get())
        add("implementation", libs.findLibrary("androidx.appCompat").get())
        add("implementation", libs.findLibrary("android.material").get())
    }
}

internal fun Project.configureKotlin() {

    tasks.withType<KotlinCompile>().configureEach {

        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()
            val warningsAsErrors: String? by project
            allWarningsAsErrors = warningsAsErrors.toBoolean()
        }

    }

}