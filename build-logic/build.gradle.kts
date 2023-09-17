plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidHilt") {
            id = "exam.android.hilt"
            implementationClass = "com.example.exam.HiltAndroidPlugin"
        }
    }
}