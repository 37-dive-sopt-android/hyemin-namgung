package com.sopt.dive.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
    val id: String ,
    val pw: String ,
    val name: String,
    val email : String,
    val age : Int
): Parcelable
