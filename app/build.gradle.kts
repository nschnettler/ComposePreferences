import de.fayard.refreshVersions.core.versionFor

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "de.schnettler.composepreferences"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = versionFor(AndroidX.compose.compiler)
    }
    namespace = "de.schnettler.composepreferences"
}

dependencies {

    implementation(project(":compose-preferences-material3"))
    implementation(project(":compose-preferences-material"))
    implementation(AndroidX.compose.material3)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.activity.compose)
}