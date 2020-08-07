plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.1")

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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "0.1.0-dev15"
        kotlinCompilerVersion = "1.4.0-dev-withExperimentalGoogleExtensions-20200720"
    }
    kotlinOptions {
        freeCompilerArgs += listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xallow-jvm-ir-dependencies",
            "-Xskip-prerelease-check",
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }
}

dependencies {
    implementation(project(":preferences"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.0-rc")
    implementation("androidx.core:core-ktx:1.3.1")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("com.google.android.material:material:1.2.0")
    implementation("androidx.compose.foundation:foundation-layout:0.1.0-dev15")
    implementation("androidx.compose.material:material:0.1.0-dev15")
    implementation("androidx.ui:ui-tooling:0.1.0-dev15")
    implementation("androidx.preference:preference-ktx:1.1.1")
}