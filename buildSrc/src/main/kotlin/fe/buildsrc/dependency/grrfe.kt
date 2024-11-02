package fe.buildsrc.dependency

import de.fayard.refreshVersions.core.DependencyGroup
import de.fayard.refreshVersions.core.DependencyNotation
import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.kotlin.dsl.IsNotADependency

object Grrfe : DependencyGroup(group = "com.gitlab.grrfe") {
    val ext = Ext

    object Ext : IsNotADependency {
        val kotlin = DependencyNotation(group = group, name = "kotlin-ext")
    }

    val gsonExt = GsonExt

    object GsonExt : DependencyNotationAndGroup(group = "$group.gson-ext", name = "gson-ext") {
        val core = module("core")
    }

    val kotlinReflectHelper = DependencyNotation(group = group, name = "kotlin-reflect-helper")
}
