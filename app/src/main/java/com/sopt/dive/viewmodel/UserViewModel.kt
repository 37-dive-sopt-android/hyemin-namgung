package com.sopt.dive.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.SavedStateHandle
import com.sopt.dive.data.ServicePool
import com.sopt.dive.data.dto.RequestLoginDto
import com.sopt.dive.data.dto.RequestSignupDto
import com.sopt.dive.data.dto.ResponseLoginDto
import com.sopt.dive.data.dto.ResponseUserDto
import com.sopt.dive.data.dto.ResponseUserBodyDto
import com.sopt.dive.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// UserViewMpdel에서 관리해야할 State : UI에서 관리하는 것을 데이터 클래스로 보관하고 State만 렌더링
/*
id , pw, 닉네임, 생일 => 로그인과 회원가입 창에서 사용할 데이터
* */
class UserViewModel(
    private val savedStateHandle: SavedStateHandle //?
) : ViewModel() {
    companion object {
        private const val USER = "user"
    }

    private val userService by lazy { ServicePool.userService }
    private val authService by lazy { ServicePool.authService }

    private val _loginSuccess = MutableStateFlow<Boolean?>(null)
    val loginSuccess = _loginSuccess.asStateFlow()

    private val _loggedInUserId = MutableStateFlow<Int?>(null)
    val loggedInUserId: StateFlow<Int?> = _loggedInUserId.asStateFlow()

    private val _userDetail = MutableStateFlow<User?>(null)
//    val userDetail: StateFlow<User?> = _userDetail.asStateFlow()

    private var _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    fun signUpUser(request: RequestSignupDto) {
        userService.signup(request).enqueue(object : Callback<ResponseUserBodyDto> {

            override fun onResponse(
                call: Call<ResponseUserBodyDto>,
                response: Response<ResponseUserBodyDto>
            ) {
                Log.d("signup_status", "HTTP status: ${response.code()}")
                if (response.isSuccessful) {
                    val user = response.body()?.data?.toUser()
                    _currentUser.value = user
                    _userDetail.value = user
                    Log.d("signup success", "회원가입 성공: ${response.body()}")
                } else {
                    Log.e("signup error", "Server error: ${response.code()}/ 메시지: ${
                        response.errorBody()?.string()
                    }")
                }
            }

            override fun onFailure(call: Call<ResponseUserBodyDto>, t: Throwable) {
                Log.e("signup failure", t.message.toString())
            }
        })
    }


    fun loginUser(request : RequestLoginDto){
        authService.login(request).enqueue(object : Callback<ResponseLoginDto>{
            override fun onResponse(call: Call<ResponseLoginDto>, response: Response<ResponseLoginDto>) {
                Log.d("login_status", "HTTP status: ${response.code()}")
                if (response.isSuccessful) {
                    val body = response.body()
                    _loggedInUserId.value = body?.data?.userId
                    _loginSuccess.value = body?.success == true
                    Log.d("login_success", "로그인 성공: $body")
                    _loggedInUserId.value?.let { fetchUser(it) }
                } else {
                    _loginSuccess.value = false
                    Log.e("login_error", "오류: ${response.code()} / ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
               Log.e("login_failure",t.message.toString())
                _loginSuccess.value = false
            }
        })
    }

    fun fetchUser(userId: Int) {
        userService.fetchUserInfo(userId).enqueue(object : Callback<ResponseUserBodyDto> {
            override fun onResponse(
                call: Call<ResponseUserBodyDto>,
                response: Response<ResponseUserBodyDto>
            ) {
                if (response.isSuccessful) {
                    val user = response.body()?.data?.toUser()
                    _currentUser.value = user
                    _userDetail.value = user
                    Log.d("fetch success", "유저 정보 조회 성공: $user")
                } else {
                    Log.e("fetch error", "오류: ${response.code()} / ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ResponseUserBodyDto>, t: Throwable) {
                Log.e("fetch failure", t.message.toString())
            }
        })
    }
    private fun ResponseUserDto.toUser(): User {
        return User(
            id = this.username,
            pw = "",
            name = this.name,
            email = this.email,
            age = this.age
        )
    }

    fun logoutUser() {
        _currentUser.value = null
        savedStateHandle[USER] = null
        _loggedInUserId.value = null
    }

}
