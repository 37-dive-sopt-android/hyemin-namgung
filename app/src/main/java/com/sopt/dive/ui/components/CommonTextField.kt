package com.sopt.dive.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CommonTextField(
    placeMessage: String,
    keyboardOptions: KeyboardOptions,
    visualTransformation  : VisualTransformation? = null
) {

    var text by remember { mutableStateOf("") }


    TextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.Transparent
            ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White, unfocusedContainerColor = Color.Transparent
        ),
        placeholder = { Text(placeMessage) },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation ?: VisualTransformation.None

    )
}
