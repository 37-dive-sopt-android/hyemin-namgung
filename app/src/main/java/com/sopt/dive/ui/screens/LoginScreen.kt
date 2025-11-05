import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.sopt.dive.model.User
import com.sopt.dive.ui.components.CommonButton
import com.sopt.dive.ui.components.CommonInputField
import com.sopt.dive.ui.validators.InputValidators
import com.sopt.dive.util.ErrorMessages

@Composable
fun LoginScreen(
    userData: User?,
    onLoginSuccess: (User) -> Unit,
    onSignUpClick: () -> Unit
) {
    val context = LocalContext.current
    var idText by remember { mutableStateOf("") }
    var pwText by remember { mutableStateOf("") }
    var errorMessage = ""

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
            placeMessage = "아이디를 입력해주세요"      ,
            keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        ),
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
        )

        Spacer(modifier = Modifier.weight(1f))

        CommonButton(
            onClick = {
                val user = userData
                if (user == null) {
                    Toast.makeText(context, "회원가입 후 로그인해주세요.", Toast.LENGTH_SHORT).show()
                } else if (idText == user.id && pwText == user.pw) {
                    Toast.makeText(context, "로그인 성공!", Toast.LENGTH_SHORT).show()
                    onLoginSuccess(user)
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
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
                color = Color.Gray
            )
        )
    }
}
