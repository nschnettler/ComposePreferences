import de.fayard.refreshVersions.bootstrapRefreshVersions

rootProject.name = "ComposePreferences"

include(":datastore:compose")
include(":datastore:manager")
include(":app")


buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:0.9.7")
}

bootstrapRefreshVersions()