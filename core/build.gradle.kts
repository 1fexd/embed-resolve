plugins {
    kotlin("jvm")
    id("net.nemerosa.versioning") version "3.1.0"
    `maven-publish`
}

group = "fe.embed-resolve"
version = versioning.info.tag ?: versioning.info.full

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    api(platform("com.github.1fexd:super:0.0.5"))

    api("com.gitlab.grrfe.gson-ext:core")
    api("com.github.1fexd:uriparser")
    api("com.github.1fexd:tld-lib")
    api("com.github.1fexd:signifykt:0.0.5")
}
