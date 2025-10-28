package com.sopt.dive.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonInputField(
    text: String,
    value: String,
    placeMessage: String,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation? = null,
    errorMessage: String? = null,
    onValueChange: (String) -> Unit
) {

    Column() {

        Text(text.uppercase().toString(), modifier = Modifier.fillMaxWidth())

        TextField(
            value = value,
            onValueChange = { onValueChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent).padding(bottom = 40.dp),
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
            singleLine = true, // 여기서 키보드 타입은 pw만 쓰이는데 imeAction만 따로 받는게 나은건지 아니면 같이 받는게 나은지
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation ?: VisualTransformation.None

        )
    }


}

