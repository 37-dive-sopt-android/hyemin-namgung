package com.sopt.dive.data.repository

import com.sopt.dive.data.api.UserService
import com.sopt.dive.data.dto.RequestSignupDto
import com.sopt.dive.data.dto.ResponseUserDto
import retrofit2.Call

class UserRepository (
    private val service : UserService
) {
    fun signUp(body : RequestSignupDto): Call<ResponseUserDto>{
        return service.signup(body)
    }
    fun fetchUser(id :Int): Call<ResponseUserDto> {
        return service.fetchUserInfo(id)
    }
}
