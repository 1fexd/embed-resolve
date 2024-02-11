package fe.embed.resolve

import fe.embed.resolve.config.ConfigType
import fe.embed.resolve.config.ConfigV1
import fe.embed.resolve.config.parseConfig
import fe.embed.resolve.manifest.Manifest
import fe.embed.resolve.resolver.ResolverV1
import fe.gson.GlobalGsonContext
import fe.signify.PublicKey
import fe.signify.Signature
import java.io.File
import java.io.InputStream

object EmbedResolver {
    const val supportsVersion = "1.x.x"
    const val bundledVersion = "1.0.0"
    const val remoteFileLocation = "https://raw.githubusercontent.com/1fexd/embed-resolve/master/files/"

    val publicKey = PublicKey.fromString("RWQazSQ29JJBtHn/Vze0iWHWGlkMUlKFQLOt2EdbTo4ToTx40uV8r8N/")

    fun resolve(uriString: String, configType: ConfigType): String? {
        val config = parseConfig<ConfigV1>(configType.inputStream!!)
        return ResolverV1.resolve(uriString, config)
    }

    inline fun <reified T> parseIfValid(fileStream: InputStream, signatureStream: InputStream): T? {
        val fileContent = fileStream.bufferedReader().readText()
        val signatureContent = signatureStream.bufferedReader().readLines()

        val signature = Signature.fromString(
            if (signatureContent.size == 1) signatureContent[0]
            else signatureContent[1]
        )

        return runCatching {
            publicKey.verify(signature, fileContent.toByteArray())
            GlobalGsonContext.gson.fromJson(fileContent, T::class.java)
        }.getOrNull()
    }
}
