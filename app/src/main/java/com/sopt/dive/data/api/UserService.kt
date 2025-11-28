package com.sopt.dive.data.api

import com.sopt.dive.data.dto.RequestSignupDto
import com.sopt.dive.data.dto.ResponseSuccessDto
import com.sopt.dive.data.dto.ResponseUserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @POST("api/v1/users")
    suspend fun signup(
        @Body request: RequestSignupDto
    ): Response<ResponseSuccessDto<ResponseUserDto>>

    @GET("api/v1/users/{id}")
    fun fetchUserInfo(
        @Path("id") id: Int,
    ): Response<ResponseSuccessDto<ResponseUserDto>>
}
