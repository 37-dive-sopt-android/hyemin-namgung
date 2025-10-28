package com.sopt.dive.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.components.CommonButton
import com.sopt.dive.ui.components.CommonInputField
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.ErrorMessages

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiveTheme {
                SignUpScreen()
            }
        }
    }

    private val birthdayRegex = Regex("^(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$")

    private fun isValidBirthday(birthday: String): Boolean {
        return birthdayRegex.matches(birthday)
    }

    @Composable
    fun SignUpScreen(modifier: Modifier = Modifier) {
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
            modifier = modifier
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


            CommonInputField(
                text = "id",
                value = idText,
                placeMessage = "아이디를 입력해주세요",
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                onValueChange = { idText = it })



            CommonInputField(
                text = "pw", value = pwText, "비밀번호를 입력해주세요", keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Password
                ), onValueChange = { pwText = it })



            CommonInputField(
                text = "nickname",
                value = nicknameText,
                placeMessage = "닉네임을 입력해주세요",
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                onValueChange = { nicknameText = it })



            CommonInputField(
                text = "birthday",
                value = birthdayInt,
                placeMessage = "닉네임을 입력해주세요",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
                ),
                onValueChange = { birthdayInt = it })


            Spacer(modifier = Modifier.weight(1f))

            CommonButton(
                onClick = {
                    idError =
                        (if (idText.length !in 6..10) ErrorMessages.ID_ERROR_MESSAGE else null)

                    pwError =
                        (if (pwText.length !in 8..12) ErrorMessages.PW_ERROR_MESSAGE else null)

                    nicknameError =
                        (if (nicknameText.isBlank()) ErrorMessages.NICKNAME_ERROR_MESSAGE else null)

                    birthdayError =
                        if (!isValidBirthday(birthdayInt)) ErrorMessages.BIRTHDAY_ERROR_MESSAGE else null

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
                }, textMessage = "회원가입하기"
            )

        }

    }

    @Preview
    @Composable
    private fun SignUpPreview() {
        DiveTheme {
            SignUpScreen()
        }
    }
}
