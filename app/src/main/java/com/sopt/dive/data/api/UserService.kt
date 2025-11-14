package com.sopt.dive.data.api

import com.sopt.dive.data.dto.RequestSignupDto
import com.sopt.dive.data.dto.ResponseUserBodyDto
import retrofit2.Call // 요 레트로핏 맞겠지 ?
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @POST("api/v1/users")
    fun signup(
        @Body request: RequestSignupDto
    ): Call<ResponseUserBodyDto>

    @GET("api/v1/users/{id}")
    fun fetchUserInfo(
        @Path("id") id: Int,
    ): Call<ResponseUserBodyDto>
}
