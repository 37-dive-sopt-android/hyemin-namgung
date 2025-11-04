package com.sopt.dive.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CommonButton(textMessage: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, Modifier.fillMaxWidth()) {
        Text(textMessage)
    }
}
