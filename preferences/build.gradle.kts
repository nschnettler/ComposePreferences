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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "0.1.0-dev16"
        kotlinCompilerVersion = "1.4.0-rc"
    }
    kotlinOptions {
        freeCompilerArgs += listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xallow-jvm-ir-dependencies",
            "-Xskip-prerelease-check"
        )
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.0-rc")

    // Compose
    implementation("androidx.core:core-ktx:1.3.1")
    implementation("androidx.compose.foundation:foundation-layout:0.1.0-dev16")
    implementation("androidx.ui:ui-tooling:0.1.0-dev16")
    implementation("androidx.compose.material:material:0.1.0-dev16")

    // Preferences
    api("com.github.tfcporciuncula:flow-preferences:1.3.1")
}