package fe.embed.resolve

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fe.embed.resolve.config.ConfigType
import fe.embed.resolve.resolver.ResolverV1
import fe.signify.PublicKey
import fe.signify.Signature
import java.io.InputStream

object EmbedResolver {
    const val supportsVersion = "1.x.x"
    const val bundledVersion = "1.1.0"
    const val remoteFileLocation = "https://raw.githubusercontent.com/1fexd/embed-resolve/master/files/"

    val publicKey = PublicKey.fromString("RWQazSQ29JJBtHn/Vze0iWHWGlkMUlKFQLOt2EdbTo4ToTx40uV8r8N/")

    fun resolve(uriString: String, configType: ConfigType): String? {
        return ResolverV1.resolve(uriString, configType.config)
    }

    val gson: Gson = GsonBuilder().registerTypeAdapter(Regex::class.java, RegexTypeAdapter).create()

    inline fun <reified T> parseIfValid(fileStream: InputStream, signatureStream: InputStream): T? {
        val fileContent = fileStream.bufferedReader().readText()
        val signatureContent = signatureStream.bufferedReader().readLines()

        val signature = Signature.fromString(
            if (signatureContent.size == 1) signatureContent[0]
            else signatureContent[1]
        )

        return runCatching {
            publicKey.verify(signature, fileContent.toByteArray())
            gson.fromJson(fileContent, T::class.java)
        }.getOrNull()
    }
}
