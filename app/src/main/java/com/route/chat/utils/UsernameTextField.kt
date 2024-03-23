package com.route.chat.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.chat.R
import com.route.chat.ui.theme.mainBlue

@Composable
fun UsernameTextField(state: MutableState<String>) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 16.dp)
    ) {
        TextField(
            value = state.value,
            onValueChange = { newText ->
                state.value = newText
            },
            singleLine = true,
            label = { Text(text = stringResource(id = R.string.username)) },
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = mainBlue,
                unfocusedIndicatorColor = Color.Gray,
                focusedLabelColor = Color.Gray,
                unfocusedLabelColor = Color.Gray,
                cursorColor = Color.Gray,
                focusedTrailingIconColor = mainBlue,
                unfocusedTrailingIconColor = mainBlue,
                errorContainerColor = Color.White,
                errorIndicatorColor = Color.Red,
                errorLabelColor = Color.Red,
                errorTrailingIconColor = Color.Transparent,
                errorTextColor = Color.Red,
                errorCursorColor = Color.Gray,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
    }
}


@Preview(showSystemUi = true, device = "id:pixel_8_pro", showBackground = true)
@Composable
private fun TextFieldPreview() {
    UsernameTextField(
        remember { mutableStateOf("") }
    )
}