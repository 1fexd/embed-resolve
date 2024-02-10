package fe.embed.resolve.manifest


data class Manifest(val versions: List<ManifestVersion>)

data class ManifestVersion(val latest: String, val version: String)
