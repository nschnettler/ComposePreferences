import de.fayard.refreshVersions.bootstrapRefreshVersions

rootProject.name = "ComposePreferences"
include(":app")
include(":preferences")


buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:0.9.7")
}

bootstrapRefreshVersions()