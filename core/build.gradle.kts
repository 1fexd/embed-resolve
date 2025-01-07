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

dependencies {
    implementation("org.jetbrains:annotations:_")
    api(Grrfe.ext.gson)
    api(_1fexd.signify)
    api(Grrfe.std.result.core)
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
