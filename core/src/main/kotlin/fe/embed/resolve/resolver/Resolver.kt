package fe.embed.resolve.resolver

import fe.embed.resolve.config.Config


interface Resolver<T : Config> {
    fun resolve(uriString: String, config: T): String?
}
