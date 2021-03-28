plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "de.schnettler.composepreferences"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
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
    implementation(project(":datastore:compose"))

    implementation(AndroidX.compose.material)
    implementation(AndroidX.appCompat)
    implementation("androidx.activity:activity-compose:_")
    implementation("androidx.datastore:datastore-preferences:_")
}