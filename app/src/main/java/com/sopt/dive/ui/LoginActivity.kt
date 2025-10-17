package com.sopt.dive.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.MainActivity

import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

import com.sopt.dive.ui.theme.DiveTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DiveTheme {
                LoginScreen()
            }
        }


    }

    @Composable
    fun LoginScreen(modifier: Modifier = Modifier) {
        val context = LocalContext.current

        var signupId by remember { mutableStateOf("") }
        var signupPw by remember { mutableStateOf("") }
        var signupNickname by remember { mutableStateOf("") }
        var signupBirthday by remember { mutableStateOf("") }

        val signUpLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    result.data?.let {
                        signupId = it.getStringExtra("id") ?: ""
                        signupPw = it.getStringExtra("pw") ?: ""
                        signupNickname = it.getStringExtra("nickname") ?: ""
                        signupBirthday = it.getStringExtra("birthday") ?: ""
                    }
                }
            }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .imePadding()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Welcome To SOPT",
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            Color.Transparent
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            Text(
                text = "ID",
                fontSize = 30.sp
            )
            var idText by remember { mutableStateOf("") }
            TextField(
                value = idText,
                onValueChange = { idText = it },
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.Transparent),
                placeholder = { Text("아이디를 입력해주세요") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.padding(vertical = 20.dp))


            Text(
                text = "PW",
                fontSize = 30.sp

            )
            var pwText by remember { mutableStateOf("") }
            TextField(
                value = pwText,
                onValueChange = { pwText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Password
                ),
                placeholder = {
                    Text("비밀번호를 입력해주세요")

                },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),

                )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    if (idText == signupId && pwText == signupPw && idText.isNotBlank()) {
                        Toast.makeText(context, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
                        context.startActivity(
                            Intent(context, MainActivity::class.java).apply {
                                putExtra("id", signupId)
                                putExtra("pw", signupPw)
                                putExtra("nickname", signupNickname)
                                putExtra("birthday", signupBirthday)
                            }
                        )
                    } else {
                        Toast.makeText(context, "아이디 또는 비밀번호가 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier
                    .fillMaxWidth()

            ) { Text("Welcome To Sopt") }

            Text(
                text = "회원가입하기",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp)
                    .clickable {
                        signUpLauncher.launch(Intent(context, SignUpActivity::class.java))
                    },
                textAlign = TextAlign.Center,
                fontSize = 15.sp,

                style = TextStyle(textDecoration = TextDecoration.Underline, color = Color.Gray)
            )


        }
    }

    @Preview(showBackground = true)
    @Composable
    fun LoginPreview() {
        DiveTheme {
            LoginScreen()

        }
    }
}
