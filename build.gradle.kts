buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:_")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:_")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}