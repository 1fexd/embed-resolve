import fe.buildsrc.dependency.Grrfe
import fe.buildsrc.dependency._1fexd
import fe.buildsrc.publishing.PublicationComponent
import fe.buildsrc.publishing.asProvider
import fe.buildsrc.publishing.publish

plugins {
    kotlin("jvm")
    id("net.nemerosa.versioning")
    `maven-publish`
}

group = "fe.embed-resolve"
version = versioning.info.tag ?: versioning.info.full

dependencies {
    api(platform("com.github.1fexd:super:_"))

    implementation("org.jetbrains:annotations:26.0.1")
    api(Grrfe.ext.gson)
    api("com.github.1fexd:tld-lib:_")
    api(_1fexd.signify)
    api(Grrfe.std.uri)

    testImplementation(kotlin("test"))
    testImplementation("com.willowtreeapps.assertk:assertk:_")
}

kotlin {
    jvmToolchain(21)
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing.publish(
    project,
    group.toString(),
    versioning.asProvider(project),
    PublicationComponent.JAVA
)
