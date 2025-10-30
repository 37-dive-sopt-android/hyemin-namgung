package com.sopt.dive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sopt.dive.model.User
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.Navigator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = intent.getSerializableExtra("user") as? User

        setContent {
            DiveTheme {
                Navigator(user = user ?: User("", "", "", ""))
            }
        }
    }
}

