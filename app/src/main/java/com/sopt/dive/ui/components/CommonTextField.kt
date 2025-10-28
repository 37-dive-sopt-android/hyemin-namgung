package com.sopt.dive.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp

@Composable
fun CommonTextField(
    value: String,
    placeMessage: String,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation? = null,
    errorMessage: String? = null,
    onValueChange: () -> Unit
) {



    TextField(
        value = value,
        onValueChange = {onValueChange},
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White, unfocusedContainerColor = Color.Transparent
        ),
        isError = errorMessage != null,
        supportingText = {
            if (errorMessage != null) {
                Text(
                    text = errorMessage, fontSize = 12.sp
                )
            }
        },
        placeholder = { Text(placeMessage) },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation ?: VisualTransformation.None

    )
}

