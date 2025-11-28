package com.sopt.dive.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

enum class Status { ACTIVE }

@Serializable
data class ResponseUserDto(
    @SerialName("id")
    val id: Long,

    @SerialName("username")
    val username: String,

    @SerialName("name")
    val name: String,

    @SerialName("email")
    val email: String,

    @SerialName("age")
    val age: Int,

    @SerialName("status")
    val status: Status
)
