package com.sopt.dive.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.model.User
import com.sopt.dive.ui.components.CommonButton
import com.sopt.dive.ui.components.CommonInputField
import com.sopt.dive.ui.validators.InputValidators
import com.sopt.dive.util.ErrorMessages

@Composable
fun SignUpScreen(
    onSignUpComplete: (User) -> Unit
) {
    val context = LocalContext.current
    var idText by remember { mutableStateOf("") }
    var pwText by remember { mutableStateOf("") }
    var nicknameText by remember { mutableStateOf("") }
    var birthdayInt by remember { mutableStateOf("") }


    val idError = if (idText.isNotBlank() && !InputValidators.isValidId(idText)
       ) {
        ErrorMessages.ID_ERROR_MESSAGE
    } else null

    val pwError = if (pwText.isNotBlank() && !InputValidators.isValidPw(pwText)) {
        ErrorMessages.PW_ERROR_MESSAGE
    } else null

    val nicknameError = if (nicknameText.isNotBlank() && !InputValidators.isValidNickName(nicknameText)) {
        ErrorMessages.NICKNAME_ERROR_MESSAGE
    } else null

    val birthdayError = if (birthdayInt.isNotBlank() && !InputValidators.isValidBirthday(birthdayInt)) {
        ErrorMessages.BIRTHDAY_ERROR_MESSAGE
    } else null

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "SIGN UP",
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
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ),
            errorMessage = idError

        )

        CommonInputField(
            titleText = "pw",
            value = pwText,
            onValueChange = { pwText = it },
            placeMessage = "비밀번호를 입력해주세요",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation(),
            errorMessage = pwError
        )

        CommonInputField(
            titleText = "nickname",
            value = nicknameText,
            onValueChange = { nicknameText = it },
            placeMessage = "닉네임을 입력해주세요", keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ),
            errorMessage = nicknameError
        )

        CommonInputField(
            titleText = "birthday",
            value = birthdayInt,
            onValueChange = { birthdayInt = it },
            placeMessage = "생년월일을 입력해주세요", keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            errorMessage = birthdayError
        )

        Spacer(modifier = Modifier.height(24.dp))

        CommonButton(
            onClick = {
                if (
                    idError == null &&
                    pwError == null &&
                    nicknameError == null &&
                    birthdayError == null &&
                    idText.isNotBlank() && pwText.isNotBlank() && nicknameText.isNotBlank() && birthdayInt.isNotBlank()
                ) {
                    val user = User(idText, pwText, nicknameText, birthdayInt)
                    onSignUpComplete(user)
                } else {
                    Toast.makeText(context, "모든 정보를 입력해주세요", Toast.LENGTH_SHORT).show()
                }
            },
            textMessage = "회원가입하기"
        )
    }
}
