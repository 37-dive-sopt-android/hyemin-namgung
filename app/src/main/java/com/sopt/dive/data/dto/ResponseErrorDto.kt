package com.sopt.dive.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseErrorDto(

    @SerialName("success")
    val success: Boolean = false,

    @SerialName("code")
    val code: String,

    @SerialName("message")
    val message: String,

    @SerialName("data")
    val data: ErrorDataDto?

)

@Serializable
data class ErrorDataDto(

    @SerialName("code")
    val code: String,

    @SerialName("message")
    val message: String,

    @SerialName("errors")
    val errors: List<FieldErrorDto>?

)

@Serializable
data class FieldErrorDto(

    @SerialName("field")
    val field: String,

    @SerialName("value")
    val value: String,

    @SerialName("reason")
    val reason: String

)
