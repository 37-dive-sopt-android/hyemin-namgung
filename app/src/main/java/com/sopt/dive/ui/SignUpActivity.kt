package com.sopt.dive.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.DiveTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiveTheme {
                SignUp()
            }
        }
    }

    fun isValidBirthday(birthday: String): Boolean {

        val regex = Regex("^(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$")
        if (regex.matches(birthday)) return true

        return false
    }

    @Composable
    fun SignUp(modifier: Modifier = Modifier) {
        val context = LocalContext.current
        var idText by remember { mutableStateOf("") }
        var pwText by remember { mutableStateOf("") }
        var nicknameText by remember { mutableStateOf("") }
        var birthdayInt by remember { mutableStateOf("") }

        var idError by remember { mutableStateOf<String?>(null) }
        var pwError by remember { mutableStateOf<String?>(null) }
        var nicknameError by remember { mutableStateOf<String?>(null) }
        var birthdayError by remember { mutableStateOf<String?>(null) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "SIGN UP",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(text = "ID")
            TextField(
                value = idText,
                onValueChange = { idText = it },
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.Transparent),
                placeholder = { Text("아이디를 입력해주세요") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White, unfocusedContainerColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                isError = idError != null,
                supportingText = {
                    if (idError != null) {
                        Text(
                            text = idError!!, fontSize = 12.sp
                        )
                    }
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(text = "PW")
            TextField(
                value = pwText,
                onValueChange = { pwText = it },
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.Transparent),
                placeholder = { Text("비밀번호를 입력해주세요") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White, unfocusedContainerColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Password
                ),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                isError = pwError != null,
                supportingText = {
                    if (pwError != null) {
                        Text(
                            text = pwError!!, fontSize = 12.sp
                        )
                    }
                },
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(text = "NICKNAME")
            TextField(
                value = nicknameText,
                onValueChange = { nicknameText = it },
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.Transparent),
                placeholder = { Text("닉네임을 입력해주세요") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White, unfocusedContainerColor = Color.Transparent
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                isError = nicknameError != null,
                supportingText = {
                    if (nicknameError != null) {
                        Text(
                            text = nicknameError!!, fontSize = 12.sp
                        )
                    }
                },
            )
            Spacer(modifier = Modifier.weight(1f))

            Text(text = "BIRTHDAY")
            TextField(
                value = birthdayInt,
                onValueChange = { birthdayInt = it },
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.Transparent),
                placeholder = { Text("생일 4자리를  입력해주세요") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White, unfocusedContainerColor = Color.Transparent
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
                ),
                isError = birthdayError != null,
                supportingText = {
                    if (birthdayError != null) {
                        Text(
                            text = birthdayError!!, fontSize = 12.sp
                        )
                    }
                })

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    idError = (if (idText.length !in 6..10) "ID는 6~10글자 사이여야 합니다. " else null)

                    pwError = (if (pwText.length !in 8..12) "비밀번호는 8 ~12 글자 사이여야 합니다. " else null)

                    nicknameError = (if (nicknameText.isBlank()) "닉네임을 입력해주세요" else null)

                    birthdayError = if (!isValidBirthday(birthdayInt)) "생일 형식으로 입력해주세요." else null

                    if (idError == null && pwError == null && nicknameError == null && birthdayError == null) {
                        Toast.makeText(context, "회원가입이 성공적으로 이루어졌습니다.", Toast.LENGTH_SHORT).show()
                        val resultIntent = Intent().apply {
                            putExtra("id", idText)
                            putExtra("pw", pwText)
                            putExtra("nickname", nicknameText)
                            putExtra("birthday", birthdayInt)
                        }
                        setResult(Activity.RESULT_OK, resultIntent)
                        finish()
                    } else {
                        Toast.makeText(context, "모든 정보를 입력해주세요", Toast.LENGTH_SHORT).show()
                    }
                }, modifier.fillMaxWidth()

            ) { Text("회원가입하기") }
        }

    }

    @Preview
    @Composable
    fun SignUpPreview() {
        DiveTheme {
            SignUp()
        }
    }
}
