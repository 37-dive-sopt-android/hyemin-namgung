package com.sopt.dive.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginDto(
    @SerialName("success")
    val success: Boolean,

    @SerialName("code")
    val code: String,

    @SerialName("message")
    val message: String,

    @SerialName("data")
    val data: LoginDataDto?
)

@Serializable
data class LoginDataDto(
    @SerialName("userId")
    val userId: Int,

    @SerialName("message")
    val message: String
)
