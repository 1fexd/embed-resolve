package fe.embed.resolve.config

import BundledJsonLoader
import fe.gson.GlobalGsonContext
import java.io.InputStream

interface Config

sealed class ConfigType(val config: ConfigV1) {
    class Bundled(config: ConfigV1) : ConfigType(config) {
        companion object {
            fun load(name: String = "bundled.json"): Bundled {
                return Bundled(parseConfig<ConfigV1>(BundledJsonLoader::class.java.getResourceAsStream(name)!!))
            }
        }
    }

//    class Remote(inputStream: InputStream) : ConfigType(inputStream) {
//        // TODO: Rework remote fetching
//    }
}


inline fun <reified T : Config> parseConfig(inputStream: InputStream): T {
    return inputStream.bufferedReader().use { GlobalGsonContext.gson.fromJson(it, T::class.java) }
}
