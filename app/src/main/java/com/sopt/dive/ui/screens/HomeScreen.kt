package com.sopt.dive.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.viewmodel.MainViewModel
import androidx.compose.runtime.getValue
import com.sopt.dive.ui.components.UserProfileCard

@Composable
fun HomeScreen(paddingValues: PaddingValues, viewModel: MainViewModel = viewModel()) {
    val homeProfileList by viewModel.homeProfileList.collectAsState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        itemsIndexed(
            items = homeProfileList,
            key = { _, user -> user.hashCode() }
        )
        { _, user ->
            UserProfileCard(user = user)
        }
    }
}

