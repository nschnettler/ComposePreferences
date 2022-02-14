rootProject.name = "ComposePreferences"

include(":compose-preferences")
include(":compose-preferences-material")
include(":compose-preferences-material3")
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
