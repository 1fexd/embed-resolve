rootProject.name = "embed-resolve"


pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://jitpack.io")
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.gitlab.grrfe.common-gradle-plugin") {
                useModule("${requested.id.id}:library:0.0.39")
            }
        }
    }
}

include("lib")

if (System.getenv("JITPACK")?.toBooleanStrictOrNull() != false) {
    include("testing")
}

//includeBuild("../signify")
//includeBuild("../tld-lib")
