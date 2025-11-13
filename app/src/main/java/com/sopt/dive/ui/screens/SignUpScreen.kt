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
import com.sopt.dive.viewmodel.UserViewModel

@Composable
fun SignUpScreen(
    userViewModel: UserViewModel,
    onSignUpComplete: (User) -> Unit
) {
    val context = LocalContext.current
    var idText by remember { mutableStateOf("") }
    var pwText by remember { mutableStateOf("") }
    var nameText by remember { mutableStateOf("") }
    var emailText by remember { mutableStateOf("") }
    var ageText by remember { mutableStateOf("") }

    val idError = if (idText.isNotBlank() && !InputValidators.isValidId(idText)) {
        ErrorMessages.ID_ERROR_MESSAGE
    } else null

    val pwError = if (pwText.isNotBlank() && !InputValidators.isValidPw(pwText)) {
        ErrorMessages.PW_ERROR_MESSAGE
    } else null

    val nameError =
        if (nameText.isNotBlank() && !InputValidators.isValidNickName(nameText)) {
            ErrorMessages.NICKNAME_ERROR_MESSAGE
        } else null

    val emailError =
        if (ageText.isNotBlank() && !InputValidators.isValidEmail(emailText)) {
            ErrorMessages.EMAIL_ERROR_MESSAGE
        } else null

    val ageError =
        if (ageText.isNotBlank() && !InputValidators.isValidAge(ageText)) {
            ErrorMessages.AGE_ERROR_MESSAGE
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
            titleText = "name",
            value = nameText,
            onValueChange = { nameText = it },
            placeMessage = "이름을 입력해주세요",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ),
            errorMessage = nameError
        )
        CommonInputField(
            titleText = "email",
            value = emailText,
            onValueChange = { emailText = it },
            placeMessage = "이메일을 입력해주세요",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
            errorMessage = emailError
        )
        CommonInputField(
            titleText = "age",
            value = ageText,
            onValueChange = { ageText = it },
            placeMessage = "나이를 입력해주세요",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            errorMessage = ageError
        )

        Spacer(modifier = Modifier.height(24.dp))

        CommonButton(
            onClick = {

                if (
                    idError == null &&
                    pwError == null &&
                    nameError == null &&
                    ageError == null &&
                    idText.isNotBlank() &&
                    pwText.isNotBlank() &&
                    nameText.isNotBlank() &&
                    ageText.isNotBlank()
                ) {
                    val user = User(idText, pwText, nameText, emailText, ageText.toInt())

                    userViewModel.signUpUser(user.id, user.pw, user.name, user.email, user.age.toInt())
                    Toast.makeText(context, "회원가입 완료! 로그인 화면으로 돌아갑니다.", Toast.LENGTH_SHORT).show()
                    onSignUpComplete(user)
                } else {
                    Toast.makeText(context, "모든 정보를 입력해주세요", Toast.LENGTH_SHORT).show()
                }
            },
            textMessage = "회원가입하기"
        )
    }
}
