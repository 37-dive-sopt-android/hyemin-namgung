package com.sopt.dive.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.sopt.dive.model.User
import com.sopt.dive.ui.components.CommonButton
import com.sopt.dive.ui.components.CommonInputField
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

        var userData by remember { mutableStateOf<User?>(null) }

        var idText by remember { mutableStateOf("") }
        var pwText by remember { mutableStateOf("") }

        val signUpLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    result.data?.let {
                        val user = it.getSerializableExtra("user") as? User
                        userData = user
                    }
                }
            }
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
                .imePadding()
        ) {
            Text(
                text = "Welcome To SOPT",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )

            CommonInputField(
                titleText = "id", value = idText,
                onValueChange = { idText = it },
                placeMessage = "아이디를 입력해주세요",
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next),
                )

            CommonInputField(
                titleText = "pw", value = pwText,
                onValueChange = { pwText = it },
                placeMessage = "비밀번호를 입력해주세요",
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation(),
            )

            Spacer(modifier = Modifier.weight(1f))

            CommonButton(
                onClick = {
                    val user = userData
                    if (user == null) {
                        Toast.makeText(context, "회원가입 후 로그인해주세요.", Toast.LENGTH_SHORT).show()
                    }
                    if (idText == user?.id && pwText == user.pw) {
                        Toast.makeText(context, "로그인 성공!", Toast.LENGTH_SHORT).show()
                        context.startActivity(Intent(context, MainActivity::class.java).apply {
                            putExtra("user", user)
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        })
                    }
                    else {
                        Toast.makeText(context,"아이디 또는 비밀번호가 틀렸습니다. ", Toast.LENGTH_SHORT).show()
                    }
                }, textMessage = "Welcome To Sopt"
            )

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
    private fun LoginPreview() {
        DiveTheme {
            LoginScreen()

        }
    }
}
