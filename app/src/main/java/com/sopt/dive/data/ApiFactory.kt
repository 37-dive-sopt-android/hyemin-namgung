package com.sopt.dive.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.dive.BuildConfig
import com.sopt.dive.data.api.AuthService
import com.sopt.dive.data.api.UserService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object ApiFactory {
    private val BASE_URL: String = BuildConfig.BASE_URL

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val json = Json {
        explicitNulls = false
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create(T::class.java)
}

object ServicePool {
    val userService: UserService by lazy {
        ApiFactory.create<UserService>()
    }

    val authService: AuthService by lazy {
        ApiFactory.create<AuthService>()
    }
}
