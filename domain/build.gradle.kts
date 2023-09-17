plugins {
    id("exam.android.library")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.domain"

}

dependencies {

    implementation(project(":data"))

    implementation(project(":model"))

    androidTestImplementation(libs.androidx.test.ext.junit)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)

}