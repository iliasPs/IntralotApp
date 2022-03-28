package com.ip.intralotapp.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.ip.intralotapp.domain.models.License

data class LicenseDTO(
    @SerializedName("key")
    val key: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("spdx_id")
    val spdxId: String,
    @SerializedName("url")
    val url: String
)

fun LicenseDTO.toLicense(): License {

    return License(
        key = key,
        name = name
    )
}