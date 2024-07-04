import de.fayard.refreshVersions.core.versionFor

plugins {
    id("com.android.library")
    kotlin("android")
    id("maven-publish")
}

android {
    compileSdk = 34

    defaultConfig {
        minSdk = 21
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
    namespace = "de.schnettler.datastore.compose.material3"
}

dependencies {
    // Manager
    api(project(":datastore-manager"))

    // Compose
    implementation(AndroidX.compose.material)
    implementation(AndroidX.compose.material3)
}

afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
            create<MavenPublication>("release") {
                // Applies the component for the release build variant.
                from(components["release"])

                // You can then customize attributes of the publication as shown below.
                groupId = "de.schnettler.composePreferences"
                artifactId = "preferences-material3"
                version = "0.1.4"
            }
        }
    }
}