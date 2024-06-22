import fe.plugin.library.LibraryConfig.Companion.library

plugins {
    id("com.gitlab.grrfe.common-gradle-plugin")
}

library("fe.embed-resolve") {
    jvm.set(17)
}

dependencies {
    implementation("fe.gson-ext:core")
    implementation("fe.uribuilder:uriparser")
    implementation("fe.signify:lib")
    implementation("fe.tld-lib:lib")

//    relocate("com.github.1fexd:tld-lib:1.0.1")
//    relocate("com.github.1fexd:signifykt:0.0.5")

//    bundle("com.gitlab.grrfe:gson-ext:11.0.0") { minimize = true }
//    bundle("com.google.code.gson:gson:2.10.1") { minimize = true }
//    bundle("com.github.1fexd:uriparser:0.0.11") { minimize = true }
//    bundle("com.github.1fexd:tld-lib:1.0.1") { minimize = true }
//    bundle("com.github.1fexd:signifykt:0.0.5") { minimize = true }
}
