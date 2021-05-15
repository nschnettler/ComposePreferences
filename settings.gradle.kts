rootProject.name = "ComposePreferences"

include(":compose-datastore")
include(":datastore-manager")
include(":app")

plugins {
    id("de.fayard.refreshVersions") version "0.10.0"
}

buildscript {
    repositories {
        gradlePluginPortal()
    }
}