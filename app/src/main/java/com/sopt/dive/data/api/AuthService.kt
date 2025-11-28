package com.sopt.dive.data.api

import com.sopt.dive.data.dto.LoginDataDto
import com.sopt.dive.data.dto.RequestLoginDto
import com.sopt.dive.data.dto.ResponseSuccessDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/auth/login")
   suspend fun login(
        @Body request: RequestLoginDto
    ): Response<ResponseSuccessDto<LoginDataDto>>
}
