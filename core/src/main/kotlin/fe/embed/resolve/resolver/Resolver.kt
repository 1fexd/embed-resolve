package fe.embed.resolve.resolver

import fe.embed.resolve.config.Config

sealed interface Resolver<in T : Config> {
    fun resolve(uriString: String, config: T): String?
}
