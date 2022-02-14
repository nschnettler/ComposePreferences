rootProject.name = "ComposePreferences"

include(":compose-preferences")
include(":datastore-manager")
include(":app")

plugins {
    id("de.fayard.refreshVersions") version "0.40.1"
}

buildscript {
    repositories {
        gradlePluginPortal()
    }
}