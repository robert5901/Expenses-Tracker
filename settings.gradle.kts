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
    }
}

rootProject.name = "Expenses tracker"
include(":app")
include(":core_api")
include(":core_impl")
include(":core")
include(":main")
include(":general")
include(":ui_atoms")
include(":add_transaction")
include(":transactions")
include(":network_api")
include(":network_impl")
