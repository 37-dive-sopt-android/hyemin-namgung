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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonInputField(
    titleText: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeMessage: String,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    errorMessage: String? = null
) {

    Column(modifier = modifier) {

        Text(titleText.uppercase().toString(), modifier = Modifier.fillMaxWidth())

        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent)
                .padding(bottom = 40.dp),
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
            visualTransformation = visualTransformation

        )
    }


}

@Preview
@Composable
private fun CommonInputFieldPreview() {
    var inputText by remember { mutableStateOf("") }
    CommonInputField(
        onValueChange = { inputText = it },
        titleText = "birthday",
        value = inputText,
        placeMessage = "닉네임을 입력해주세요",
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
        ),
        visualTransformation = PasswordVisualTransformation(),
    )

}

