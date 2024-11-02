import java.util.*
import kotlin.experimental.ExperimentalTypeInference

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
    }

    plugins {
        id("de.fayard.refreshVersions") version "0.60.5"
        id("net.nemerosa.versioning") version "3.1.0"
        kotlin("jvm") version "2.0.20"
    }
}

plugins {
    id("de.fayard.refreshVersions")
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://maven.mozilla.org/maven2") }
        mavenLocal()
    }
}

rootProject.name = "embed-resolve"
include(":core")

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


val isCI = hasEnv("CI")
val isJitPack = hasEnv("JITPACK")

val localProperties = file("local.properties")
val devProperties: Properties? = if (localProperties.exists()) {
    Properties().apply {
        localProperties.reader().use { load(it) }
    }
} else null

val isDev = (devProperties?.get("dev")?.toString()?.toBooleanStrictOrNull() == true)

if (devProperties != null && isDev && (!isCI && !isJitPack)) {
    devProperties["kotlin-ext.dir"]?.trySubstitute("com.gitlab.grrfe.kotlin-ext") {
        this["core"] = "core"
        this["io"] = "io"
        this["java-time"] = "java-time"
        this["result-core"] = "result:result-core"
        this["result-assert"] = "result:result-assert"
        this["uri"] = "uri"
    }

    devProperties["gson-ext.dir"].trySubstitute("com.gitlab.grrfe.gson-ext") {
        this["core"] = "core"
    }

    devProperties["httpkt.dir"].trySubstitute("com.gitlab.grrfe.httpkt") {
        this["core"] = "core"
        this["ext-gson"] = "ext-gson"
    }

    devProperties["koin-app.dir"].trySubstitute("com.gitlab.grrfe.koin-app") {
        this["core"] = "core"
        this["api"] = "api"
        this["cli-core"] = "cli:cli-core"
        this["exposed-core"] = "exposed:exposed-core"
        this["exposed-sqlite"] = "exposed:exposed-sqlite"
    }
}
