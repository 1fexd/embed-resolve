package fe.embed.resolve.resolver

import fe.embed.resolve.config.ConfigV1
import fe.uribuilder.UriParseResult
import fe.uribuilder.UriParser

object ResolverV1 : Resolver<ConfigV1> {
    override fun resolve(uriString: String, config: ConfigV1): String? {
        val result = UriParser.parseUri(uriString)
        if (result !is UriParseResult.ParsedUri) return null

        val host = result.host ?: return null
        val service = config.services.firstOrNull { host in it.embedDomains } ?: return null

        val path = result.getPath()
        if (service.ignorePattern?.matches(path) == true) return null

        val match = service.pattern.matchEntire(path) ?: return null

        val (_, relevantPath) = match.groupValues
        return "${result.scheme}://${service.domain}$relevantPath"
    }
}
