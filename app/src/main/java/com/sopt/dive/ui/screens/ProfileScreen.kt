package com.sopt.dive.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.MainPinkBackground
import com.sopt.dive.viewmodel.UserViewModel
import androidx.compose.runtime.getValue

@Composable
fun ProfileScreen(paddingValues: PaddingValues, userViewModel: UserViewModel) {
    val user by userViewModel.currentUser.collectAsState()
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

        Text(text = "ID: ${user?.id}", fontSize = 20.sp)

        Text(text = "PW: ${user?.pw}", fontSize = 20.sp)

        Text(text = "Name: ${user?.name}", fontSize = 20.sp)

        Text(text = "Email: ${user?.email}", fontSize = 20.sp)

        Text(text = "Age: ${user?.age}", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { userViewModel.logoutUser() },
            colors = ButtonDefaults.buttonColors(
                containerColor = MainPinkBackground,
                contentColor = Color.White
            )
        ) {
            Text("로그아웃")
        }
    }
}
