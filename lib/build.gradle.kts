import fe.plugin.library.LibraryConfig.Companion.library

plugins {
    id("com.gitlab.grrfe.common-gradle-plugin")
}

library("fe.embed-resolve") {
    jvm.set(17)
}

dependencies {
    relocate("com.gitlab.grrfe:gson-ext:11.0.0")
    relocate("com.google.code.gson:gson:2.10.1")
    relocate("com.github.1fexd:uriparser:0.0.10")
    relocate("com.github.1fexd:tld-lib:1.0.1")
    relocate("com.github.1fexd:signifykt:0.0.5")
}

//afterEvaluate {
//    tasks.withType<ShadowJar>() {
//
//    }

//    tasks.getByName(ShadowJavaPlugin.SHADOW_JAR_TASK_NAME) {
//        from(project.rootDir) {
//            include("files/1.0.0.json")
//        }
//    }
//}
