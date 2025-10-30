package com.sopt.dive.model

import java.io.Serializable


data class User(
    val id: String ,
    val pw: String ,
    val nickname: String,
    val birthday: String
) : Serializable
