plugins {
    kotlin("jvm")
    id("net.nemerosa.versioning")
    `maven-publish`
}

group = "fe.embed-resolve"
version = versioning.info.tag ?: versioning.info.full

dependencies {
    api(platform("com.github.1fexd:super:_"))

    api("com.gitlab.grrfe.gson-ext:core:_")
    api("com.github.1fexd:uriparser:_")
    api("com.github.1fexd:tld-lib:_")
    api("com.github.1fexd:signifykt:_")
    implementation("org.jetbrains:annotations:26.0.1")

    testImplementation(kotlin("test"))
    testImplementation("com.willowtreeapps.assertk:assertk:_")
}
