package com.sopt.dive.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// 저번 코드리뷰에서 이넘은 스트링으로 반환된다했으니 이렇게 해도 되지 않을까 ? 안에 값도 문자열로 가는거 아닌가 ,,?
enum class Status { ACTIVE }

@Serializable
data class ResponseSignupDto(
    @SerialName("id")
    val id: Int,

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
