package fe.embed.resolve.config

import com.google.gson.annotations.SerializedName

data class ConfigV1(val services: List<ServiceV1>) : Config

data class ServiceV1(
    val name: String,
    @SerializedName(value = "resolve_to") val resolveTo: String,
    @SerializedName(value = "embed_domain") val embedDomain: String
)
