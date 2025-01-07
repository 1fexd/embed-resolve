package fe.embed.resolve

import fe.embed.resolve.config.ConfigV1
import fe.embed.resolve.resolver.ResolverV1


class EmbedResolver(private val config: ConfigV1) {

    fun resolve(url: String): String? {
        return ResolverV1.resolve(url, config)
    }
}
