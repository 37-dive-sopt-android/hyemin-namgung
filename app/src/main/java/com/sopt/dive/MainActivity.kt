package com.sopt.dive

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.LoginActivity
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.IntentKeys

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = intent.getStringExtra(IntentKeys.ID)
        val pw = intent.getStringExtra(IntentKeys.PW)
        val nickname = intent.getStringExtra(IntentKeys.NICKNAME)
        val birthday = intent.getStringExtra(IntentKeys.BIRTHDAY)

        setContent {
            DiveTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MainScreen(
                        id = id ?: "", pw = pw ?: "",
                        nickname = nickname ?: "",
                        birthday = birthday ?: "",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

    }

    @Composable
    fun MainScreen(
        id: String,
        pw: String,
        nickname: String,
        birthday: String,
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = " 가입을 축하합니다 !  ",
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = " 가입 정보 확인  ", fontSize = 20.sp, color = Color.Gray
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            Text(text = "ID", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text(text = id, fontSize = 20.sp)


            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Text(text = "PW", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text(text = pw, fontSize = 20.sp)


            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Text(text = "NICKNAME", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text(text = nickname, fontSize = 20.sp)
            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            Text(text = "BIRTHDAY", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text(text = birthday, fontSize = 20.sp)
            val context = LocalContext.current
            Spacer(modifier = Modifier.padding(vertical = 80.dp))
            Button(
                onClick = { context.startActivity(Intent(context, LoginActivity::class.java)) },

                modifier = Modifier.fillMaxWidth()
            ) { Text("되돌아가기") }
        }
    }

    @Preview
    @Composable
    fun MainPreview() {
        MainScreen(
            id = "dafault_id",
            pw = "dafault_pw",
            nickname = "dafault_nickname",
            birthday = "dafault_birthday",
        )
    }

}
