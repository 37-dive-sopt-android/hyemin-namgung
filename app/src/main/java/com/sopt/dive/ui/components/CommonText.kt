package com.sopt.dive.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CommonText(text:String){
    Text(text, modifier = Modifier.fillMaxWidth())
}
