package com.sopt.dive.viewmodel

import androidx.lifecycle.ViewModel
import com.sopt.dive.model.HomeProfileInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// MainViewModel에서 갱신해줘야할 State : UI에서 관리하는 것을 데이터 클래스로 보관하고 State만 렌더링

class MainViewModel : ViewModel() {
    // 내부 전용으로만 사용해서 밖에서 변경하지 못하도록 (변경 가능한 데이터 흐름)
    private val _homeProfileList = MutableStateFlow<List<HomeProfileInfo>>(emptyList())

    // 외부 전용으로만 사용해서 읽기 전용으로 사용
    val homeProfileList: StateFlow<List<HomeProfileInfo>> = _homeProfileList.asStateFlow()

    init {
        loadHomeProfileList()
    }

    private fun loadHomeProfileList() {
        _homeProfileList.value = listOf(
            HomeProfileInfo(userName = "지니", title = "집 보내주세요", content = "일하기 싫어요 "),
            HomeProfileInfo(userName = "지니", title = "집 보내주세요2", content = "일하기 싫어요 "),
            HomeProfileInfo(userName = "지니", title = "집 보내주세요3", content = "일하기 싫어요 "),
            HomeProfileInfo(userName = "지니", title = "집 보내주세요4", content = "일하기 싫어요 "),
            HomeProfileInfo(userName = "지니", title = "집 보내주세요5", content = "일하기 싫어요 "),
            HomeProfileInfo(userName = "지니", title = "집 보내주세요6", content = "일하기 싫어요 "),
            HomeProfileInfo(userName = "지니", title = "집 보내주세요7", content = "일하기 싫어요 "),
            HomeProfileInfo(userName = "지니", title = "집 보내주세요8", content = "일하기 싫어요 "),
            HomeProfileInfo(userName = "지니", title = "집 보내주세요9", content = "일하기 싫어요 "),
            HomeProfileInfo(userName = "지니", title = "집 보내주세요10", content = "일하기 싫어요 "),
            HomeProfileInfo(userName = "지니", title = "집 보내주세요11", content = "일하기 싫어요 "),
            HomeProfileInfo(userName = "지니", title = "집 보내주세요12", content = "일하기 싫어요 "),
            HomeProfileInfo(userName = "지니", title = "집 보내주세요13", content = "일하기 싫어요 "),
            HomeProfileInfo(userName = "지니", title = "집 보내주세요14", content = "일하기 싫어요 "),
            HomeProfileInfo(userName = "지니", title = "집 보내주세요15", content = "일하기 싫어요 "),
        )
    }

}

