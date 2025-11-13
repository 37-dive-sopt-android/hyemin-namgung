package com.sopt.dive.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginDto(
    @SerialName("userId")
    val userId : Int,

    @SerialName("message")
    val message : String
)
