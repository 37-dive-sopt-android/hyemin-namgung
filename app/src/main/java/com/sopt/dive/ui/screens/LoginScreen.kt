package com.sopt.dive.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.viewmodel.MainViewModel
import com.sopt.dive.ui.components.CommonButton
import com.sopt.dive.ui.components.CommonInputField


@Composable
fun LoginScreen(
    mainViewModel: MainViewModel,
    onLoginSuccess: (String, String) -> Unit,
    onSignUpClick: () -> Unit
) {
    val context = LocalContext.current

    var idText by remember { mutableStateOf("") }
    var pwText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            text = "Welcome To SOPT",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )

        CommonInputField(
            titleText = "id",
            value = idText,
            onValueChange = { idText = it },
            placeMessage = "아이디를 입력해주세요",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text)
        )

        CommonInputField(
            titleText = "pw",
            value = pwText,
            onValueChange = { pwText = it },
            placeMessage = "비밀번호를 입력해주세요",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.weight(1f))

        CommonButton(
            onClick = {
                if (mainViewModel.loginUser(idText, pwText)) {
                    onLoginSuccess(idText, pwText)
                } else {
                    Toast.makeText(context, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            },
            textMessage = "로그인"
        )

        Text(
            text = "회원가입하기",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp)
                .clickable { onSignUpClick() },
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline, color = Color.Gray)
        )
    }
}
