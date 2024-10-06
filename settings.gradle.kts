import java.util.*
import kotlin.experimental.ExperimentalTypeInference

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

fun substitute(directory: Any, dependency: String, substitutes: Map<String, String>) {
    includeBuild(directory) {
        dependencySubstitution {
            for ((artifact, project) in substitutes) {
                substitute(module("$dependency:$artifact")).using(project(":$project"))
            }
        }
    }
}

@OptIn(ExperimentalTypeInference::class)
fun Any?.trySubstitute(
    dependency: String,
    @BuilderInference builderAction: MutableMap<String, String>.() -> Unit = {},
) {
    this?.let { substitute(this, dependency, buildMap(builderAction)) }
}


fun hasEnv(name: String): Boolean {
    return System.getenv(name)?.toBooleanStrictOrNull() == true
}

rootProject.name = "embed-resolve"
include(":core")

val isCI = hasEnv("CI")
val isJitPack = hasEnv("JITPACK")

val substitutes = file("local.properties")
if (substitutes.exists() && !isCI && !isJitPack) {
    include(":testing")

    val properties = Properties().apply {
        file("local.properties").reader().use { load(it) }
    }

    properties["gson-ext.dir"].trySubstitute("com.gitlab.grrfe:gson-ext") {
        this["core"] = "core"
    }

    properties["tld-lib.dir"]?.trySubstitute("com.github.1fexd:tld-lib") {
        this[":"] = "lib"
    }

    properties["uriparser.dir"]?.trySubstitute("com.github.1fexd:uriparser")
    properties["signify.dir"]?.trySubstitute("com.github.1fexd:signifykt")
}

