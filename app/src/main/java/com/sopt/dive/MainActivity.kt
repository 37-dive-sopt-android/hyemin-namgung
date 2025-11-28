package com.sopt.dive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.Navigator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DiveTheme {
                val navController = rememberNavController()
                Navigator(navController)
            }
        }
    }
}

