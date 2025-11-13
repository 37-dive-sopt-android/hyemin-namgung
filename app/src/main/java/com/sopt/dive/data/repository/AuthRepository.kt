package com.sopt.dive.data.repository

import androidx.compose.ui.autofill.Autofill
import com.sopt.dive.data.api.AuthService
import com.sopt.dive.data.api.UserService
import com.sopt.dive.data.dto.RequestLoginDto
import com.sopt.dive.data.dto.RequestSignupDto
import com.sopt.dive.data.dto.ResponseLoginDto
import com.sopt.dive.data.dto.ResponseUserDto
import retrofit2.Call

class AuthRepository (
    private val service : AuthService
) {
    fun login(body : RequestLoginDto): Call<ResponseLoginDto>{
        return service.login(body)
    }
}
