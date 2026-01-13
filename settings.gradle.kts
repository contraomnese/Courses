pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "Courses"

include(":app")
include(":core:navigation")
include(":core:presentation")
include(":core:design")
include(":core:ui")
include(":data")
include(":domain")

include(":features:auth")
include(":features:home")
include(":features:bottom_menu")
include(":features:account")
