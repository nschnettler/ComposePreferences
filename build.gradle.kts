buildscript {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-alpha12")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.30")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
        kotlinOptions.freeCompilerArgs += listOf( "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi")
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}