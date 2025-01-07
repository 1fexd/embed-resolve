package fe.embed.resolve.loader

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fe.embed.resolve.RegexTypeAdapter
import fe.embed.resolve.Resource
import fe.embed.resolve.config.ConfigSerializer
import fe.embed.resolve.config.ConfigV1
import fe.signify.PublicKey
import fe.signify.Signature
import java.io.InputStream
import java.net.URL

interface EmbedResolveConfigLoader {
    fun load(): Result<ConfigV1?>
}

object BundledEmbedResolveConfigLoader : EmbedResolveConfigLoader {
    private const val file = "embed_resolve.json"
    private val bundledClass = Resource::class.java
    private val bundledClassPackage by lazy {
        bundledClass.`package`.name.replace(".", "/")
    }

    private val url by lazy {
        bundledClass.getResource(file)
            ?: getSystemResource(bundledClassPackage)
            ?: getSystemResource("fe/embed/resolve")
            ?: getSystemResource()
    }

    fun getSystemResource(path: String? = null): URL? {
        val filePath = path?.let { "$it/$file" } ?: file
        return ClassLoader.getSystemResource(filePath)
    }

    override fun load(): Result<ConfigV1?> {
        return runCatching {
            println(url)
            url?.openStream()?.let { ConfigSerializer.parseConfig<ConfigV1>(it) }
        }
    }
}

class StreamEmbedResolveConfigLoader(private val stream: InputStream) : EmbedResolveConfigLoader {
    override fun load(): Result<ConfigV1?> {
        return runCatching {
            ConfigSerializer.parseConfig<ConfigV1>(stream)
        }
    }
}

object RemoteLoader {
    val gson: Gson = GsonBuilder().registerTypeAdapter(Regex::class.java, RegexTypeAdapter).create()
    val publicKey = PublicKey.fromString("RWQazSQ29JJBtHn/Vze0iWHWGlkMUlKFQLOt2EdbTo4ToTx40uV8r8N/")

    inline fun <reified T> parseIfValid(fileStream: InputStream, signatureStream: InputStream): T? {
        val fileContent = fileStream.bufferedReader().readText()

        val signatureContent = signatureStream.bufferedReader().readLines()
        // TODO: Catch
        val signature = Signature.fromString(signatureContent.singleOrNull() ?: signatureContent[1])

        return runCatching {
            publicKey.verify(signature, fileContent.toByteArray())
            gson.fromJson(fileContent, T::class.java)
        }.getOrNull()
    }
}



