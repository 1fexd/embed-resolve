import fe.buildsrc.dependency.Grrfe
import fe.buildsrc.dependency._1fexd


plugins {
}

dependencies {
    implementation("org.jetbrains:annotations:_")
    api(Grrfe.ext.gson)
    api(_1fexd.signify)
    api(Grrfe.std.result.core)
    api(Grrfe.std.uri)

    testImplementation(kotlin("test"))
    testImplementation("com.willowtreeapps.assertk:assertk:_")
//    testImplementation(Grrfe.std.test)
}
