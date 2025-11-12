package com.sopt.dive.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sopt.dive.ui.theme.MainPinkBackground

@Composable
fun CommonButton(textMessage: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
        containerColor = MainPinkBackground,
        contentColor = Color.White
    )
        ,
    ) {
        Text(textMessage, modifier)
    }
}
