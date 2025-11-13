package com.sopt.dive.data.api

import com.sopt.dive.data.dto.RequestLoginDto
import com.sopt.dive.data.dto.ResponseLoginDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/login")
    fun login(
        @Body request: RequestLoginDto
    ): Call<ResponseLoginDto>
}
