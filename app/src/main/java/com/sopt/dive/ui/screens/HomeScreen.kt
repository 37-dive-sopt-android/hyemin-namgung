package com.sopt.dive.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.ui.components.UserProfileInfo

import com.sopt.dive.util.userProfileList


@Composable
fun HomeScreen(paddingValues: PaddingValues) {

    LazyColumn(modifier = Modifier.fillMaxSize().padding(10.dp)){
        itemsIndexed(
            items = userProfileList
        ) {index, user ->
            UserProfileInfo(user= user, modifier = Modifier)
        }


    }
}

@Preview
@Composable
private fun HomeScreenPreview(){
    HomeScreen(paddingValues = PaddingValues())
}
