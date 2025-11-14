package com.sopt.dive.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeProfileInfo(val userName: String, val title: String, val content: String) :
    Parcelable
