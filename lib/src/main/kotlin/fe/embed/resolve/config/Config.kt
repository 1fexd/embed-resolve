package fe.embed.resolve.config

import BundledJsonLoader
import fe.gson.GlobalGsonContext
import java.io.InputStream

interface Config

sealed class ConfigType(val inputStream: InputStream?) {
    data object Bundled : ConfigType(BundledJsonLoader::class.java.getResourceAsStream("bundled.json"))
    class Remote(inputStream: InputStream) : ConfigType(inputStream)
}


inline fun <reified T : Config> parseConfig(inputStream: InputStream): T {
    return inputStream.bufferedReader().use { GlobalGsonContext.gson.fromJson(it, T::class.java) }
}
