package com.ip.intralotapp.domain.models

data class Permissions(

    val maintain: Boolean,
    val pull: Boolean,
    val push: Boolean,
)
