package com.sopt.dive.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.model.User
import com.sopt.dive.ui.theme.MainPinkBackground

@Composable
fun ProfileScreen(paddingValues: PaddingValues, user: User) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = " Profile",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "ID: ${user.id}", fontSize = 20.sp)
        Text(text = "PW: ${user.pw}", fontSize = 20.sp)
        Text(text = "Nickname: ${user.nickname}", fontSize = 20.sp)
        Text(text = "Birthday: ${user.birthday}", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = MainPinkBackground,
                contentColor = Color.White
            )
        ) {
            Text("로그아웃")
        }
    }
}
