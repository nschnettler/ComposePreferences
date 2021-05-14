plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(21)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-beta03"
    }
}

dependencies {
    // Manager
    api(project(":datastore:manager"))

    implementation(Kotlin.stdlib.jdk8)

    // Compose
    implementation(AndroidX.compose.material)
    implementation("com.google.accompanist", "accompanist-insets", "_")
}