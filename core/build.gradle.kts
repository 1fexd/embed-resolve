import fe.build.dependencies.Grrfe


plugins {
}

dependencies {
    implementation("org.jetbrains:annotations:_")

    api(platform(Grrfe.gsonExt.bom))
    api(Grrfe.gsonExt.core)

    api(platform(Grrfe.std.bom))
    api(Grrfe.std.result.core)
    api(Grrfe.std.uri)
    api(Grrfe.signify)

    testImplementation(kotlin("test"))
    testImplementation("com.willowtreeapps.assertk:assertk:_")
    testImplementation(Grrfe.std.test)
}
