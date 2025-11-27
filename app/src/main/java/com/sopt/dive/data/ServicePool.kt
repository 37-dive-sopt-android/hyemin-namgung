package com.sopt.dive.data

import com.sopt.dive.data.api.AuthService
import com.sopt.dive.data.api.UserService

object ServicePool {
    val userService: UserService by lazy {
        ApiFactory.create<UserService>()
    }

    val authService: AuthService by lazy {
        ApiFactory.create<AuthService>()
    }
}
