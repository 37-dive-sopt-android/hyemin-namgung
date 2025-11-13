package com.sopt.dive.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sopt.dive.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


// UserViewMpdel에서 관리해야할 State : UI에서 관리하는 것을 데이터 클래스로 보관하고 State만 렌더링
/*
id , pw, 닉네임, 생일 => 로그인과 회원가입 창에서 사용할 데이터
* */
class UserViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        private const val USER = "user"
    }

    private var _currentUser = MutableStateFlow(savedStateHandle.get<User?>(USER))
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    fun signUpUser(userId: String, password: String, nickname: String, email: String, age: Int) {
        val newUser = User(userId, password, nickname, email, age)
        _currentUser.value = newUser
        savedStateHandle[USER] = newUser
    }

    fun loginUser(userId: String, password: String): Boolean {
        val user = _currentUser.value
        val success = user?.id == userId && user.pw == password
        if (success) savedStateHandle[USER] = user
        return success
    }

    fun logoutUser() {
        _currentUser.value = null
        savedStateHandle[USER] = null
    }

}
