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
//    implementation("com.gitlab.grrfe.gson-ext:core:16.0.0-gson2-koin3")
//    implementation("fe.uribuilder:uriparser")
//    implementation("fe.signify:lib")
    api(platform("com.github.1fexd.super:platform:0.0.1"))

    api("com.gitlab.grrfe.gson-ext:core")
    api("com.github.1fexd:uriparser")
    api("com.github.1fexd:tld-lib")
    api("com.github.1fexd.signifykt:lib")

//    bundle("com.gitlab.grrfe:gson-ext:11.0.0") { minimize = true }
//    bundle("com.google.code.gson:gson:2.10.1") { minimize = true }
//    bundle("com.github.1fexd:uriparser:0.0.11") { minimize = true }
//    bundle("com.github.1fexd:tld-lib:1.0.1") { minimize = true }
//    bundle("com.github.1fexd:signifykt:0.0.5") { minimize = true }
}
