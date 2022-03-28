package com.ip.intralotapp.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.ip.intralotapp.domain.models.Permissions

data class PermissionsDTO(
    @SerializedName("admin")
    val admin: Boolean,
    @SerializedName("maintain")
    val maintain: Boolean,
    @SerializedName("pull")
    val pull: Boolean,
    @SerializedName("push")
    val push: Boolean,
    @SerializedName("triage")
    val triage: Boolean
)

fun PermissionsDTO.toPermissions(): Permissions {

    return Permissions(
        maintain = maintain,
        pull = pull,
        push = push
    )
}

