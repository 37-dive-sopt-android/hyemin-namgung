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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.model.User
import com.sopt.dive.ui.components.CommonButton
import com.sopt.dive.ui.components.CommonInputField
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.ui.validators.InputValidators
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
                .verticalScroll(rememberScrollState())
                .imePadding()
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
                titleText = "id",
                value = idText,
                placeMessage = "아이디를 입력해주세요",
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                onValueChange = { idText = it },
                errorMessage = idError)

            CommonInputField(
                titleText = "pw",
                value = pwText,
                onValueChange = { pwText = it },
                placeMessage = "비밀번호를 입력해주세요",
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation(),
                errorMessage = pwError
            )

            CommonInputField(
                titleText = "nickname", value = nicknameText,
                onValueChange = { nicknameText = it },
                placeMessage = "닉네임을 입력해주세요",
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                errorMessage = nicknameError
            )

            CommonInputField(
                titleText = "birthday",
                value = birthdayInt,
                placeMessage = "생일을 입력해주세요",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
                ),
                onValueChange = { birthdayInt = it },
                errorMessage = birthdayError)

            Spacer(modifier = Modifier.weight(1f))

            CommonButton(
                onClick = {
                    idError =
                        (if (!InputValidators.isValidId(idText)) ErrorMessages.ID_ERROR_MESSAGE else null)

                    pwError =
                        (if (!InputValidators.isValidPw(pwText)) ErrorMessages.PW_ERROR_MESSAGE else null)

                    nicknameError =
                        (if (!InputValidators.isValidNickName(nicknameText)) ErrorMessages.NICKNAME_ERROR_MESSAGE else null)

                    birthdayError =
                        if (!InputValidators.isValidBirthday(birthdayInt)) ErrorMessages.BIRTHDAY_ERROR_MESSAGE else null

                    if (listOf(idError, pwError, nicknameError, birthdayError).all { it == null }) {
                        val user = User(id = idText, pw = pwText, nickname = nicknameText, birthday = birthdayInt)

                        val resultIntent = Intent().apply { putExtra("user", user) }

                        setResult(Activity.RESULT_OK, resultIntent)
                        Toast.makeText(context, "회원가입이 완료되었습니다!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                },
                textMessage = "회원가입하기"
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
