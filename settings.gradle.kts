@file:Suppress("UNUSED_EXPRESSION")
pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven ( "https://jitpack.io" )
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven ( "https://jitpack.io" )
    }
}

rootProject.name = "To Do List"
include(":app")
 