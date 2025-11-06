package com.sopt.dive.viewmodel

import androidx.lifecycle.ViewModel
import com.sopt.dive.model.User

class MainViewModel : ViewModel() {
    data class UserInfo(val userName: String, val title: String, val content: String)

    val userProfileList = listOf(
        UserInfo(userName = "지니", title = "집 보내주세요", content = "일하기 싫어요 "),
        UserInfo(userName = "지니", title = "집 보내주세요2", content = "일하기 싫어요 "),
        UserInfo(userName = "지니", title = "집 보내주세요3", content = "일하기 싫어요 "),
        UserInfo(userName = "지니", title = "집 보내주세요4", content = "일하기 싫어요 "),
        UserInfo(userName = "지니", title = "집 보내주세요5", content = "일하기 싫어요 "),
        UserInfo(userName = "지니", title = "집 보내주세요6", content = "일하기 싫어요 "),
        UserInfo(userName = "지니", title = "집 보내주세요7", content = "일하기 싫어요 "),
        UserInfo(userName = "지니", title = "집 보내주세요8", content = "일하기 싫어요 "),
        UserInfo(userName = "지니", title = "집 보내주세요9", content = "일하기 싫어요 "),
        UserInfo(userName = "지니", title = "집 보내주세요10", content = "일하기 싫어요 "),
        UserInfo(userName = "지니", title = "집 보내주세요11", content = "일하기 싫어요 "),
        UserInfo(userName = "지니", title = "집 보내주세요12", content = "일하기 싫어요 "),
        UserInfo(userName = "지니", title = "집 보내주세요13", content = "일하기 싫어요 "),
        UserInfo(userName = "지니", title = "집 보내주세요14", content = "일하기 싫어요 "),
        UserInfo(userName = "지니", title = "집 보내주세요15", content = "일하기 싫어요 "),

        )

        private var _currentUser: User? = null
        val currentUser: User?
            get() = _currentUser

        fun loginUser(userName: String, password: String): Boolean {
            return _currentUser?.let {
                it.id == userName && it.pw == password
            } ?: false
        }

        fun signUpUser(userName: String, password: String, nickname: String, birthday: String) {
            _currentUser = User(userName, password, nickname, birthday)
        }
    }





