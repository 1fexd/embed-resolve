package fe.embed.resolve

import fe.embed.resolve.config.ConfigType
import fe.embed.resolve.config.ConfigV1
import fe.embed.resolve.config.parseConfig
import fe.embed.resolve.manifest.Manifest
import fe.embed.resolve.resolver.ResolverV1
import fe.gson.GlobalGsonContext
import fe.signify.PublicKey
import java.io.InputStream

object EmbedResolver {
    const val supportsVersion = "1.x.x"
    const val bundledVersion = "1.0.0"
    const val remoteFileLocation = "https://github.com/1fexd/embed-resolve"

    val publicKey = PublicKey.fromString("RWQazSQ29JJBtHn/Vze0iWHWGlkMUlKFQLOt2EdbTo4ToTx40uV8r8N/")

    fun resolve(uriString: String, configType: ConfigType): String? {
        val config = parseConfig<ConfigV1>(configType.inputStream!!)
        return ResolverV1.resolve(uriString, config)
    }



//    fun parseManifest(inputStream: InputStream): Manifest {
//        return inputStream.bufferedReader().use { GlobalGsonContext.gson.fromJson(it, Manifest::class.java) }
//    }
//
//    fun checkHasNewerSupportedVersion(manifest: Manifest): String? {
//        val version = manifest.versions.find { it.version == supportsVersion } ?: return null
//        return if (version.latest != bundledVersion) version.latest else null
//    }
}
