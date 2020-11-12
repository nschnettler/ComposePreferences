import de.fayard.refreshVersions.bootstrapRefreshVersions

rootProject.name = "ComposePreferences"
include(":app")
include(":preferences")
include(":datastorePreferences")


buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:0.9.7")
}

bootstrapRefreshVersions()