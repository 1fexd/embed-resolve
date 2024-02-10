package fe.embed.resolve.resolver

import fe.embed.resolve.config.ConfigV1
import fe.uribuilder.UriParseResult
import fe.uribuilder.UriParser

object ResolverV1 : Resolver<ConfigV1> {
    override fun resolve(uriString: String, config: ConfigV1): String? {
        val result = UriParser.parseUri(uriString)
        if (result !is UriParseResult.ParsedUri) return null

        val host = result.host ?: return null
        val service = config.services.firstOrNull { it.embedDomain == host } ?: return null

        val startIdx = uriString.indexOf(host)
        val endIdx = startIdx + host.length

        val newUri = uriString.substring(0, startIdx) + service.resolveTo + uriString.substring(endIdx)
        return newUri
    }
}
