import fe.plugin.library.LibraryConfig.Companion.library
import fe.plugin.library.bundle

plugins {
    id("com.gitlab.grrfe.common-gradle-plugin")
}

library("fe.embed-resolve") {
    jvm.set(17)
}

dependencies {
    relocate("com.gitlab.grrfe:gson-ext:11.0.0")
    relocate("com.google.code.gson:gson:2.10.1")
    relocate("com.github.1fexd:uriparser:0.0.11")
    relocate("com.github.1fexd:tld-lib:1.0.1")
    relocate("com.github.1fexd:signifykt:0.0.5")

//    bundle("com.gitlab.grrfe:gson-ext:11.0.0") { minimize = true }
//    bundle("com.google.code.gson:gson:2.10.1") { minimize = true }
//    bundle("com.github.1fexd:uriparser:0.0.11") { minimize = true }
//    bundle("com.github.1fexd:tld-lib:1.0.1") { minimize = true }
//    bundle("com.github.1fexd:signifykt:0.0.5") { minimize = true }
}
