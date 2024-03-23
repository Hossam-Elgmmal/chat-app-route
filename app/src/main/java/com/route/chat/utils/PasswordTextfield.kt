package com.route.chat.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.chat.R
import com.route.chat.ui.theme.mainBlue

@Composable
fun PasswordTextField(passwordState: MutableState<String>, errorMessage: String?) {

    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 16.dp)
    ) {
        TextField(
            value = passwordState.value,
            onValueChange = { newText ->
                passwordState.value = newText
            },
            singleLine = true,
            isError = errorMessage != null,
            label = { Text(text = stringResource(id = R.string.password)) },
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
                errorContainerColor = Color.White,
                errorIndicatorColor = Color.Red,
                errorLabelColor = Color.Red,
                errorTrailingIconColor = Color.DarkGray,
                errorCursorColor = Color.Gray,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                if (showPassword) {
                    IconButton(onClick = { showPassword = false }) {
                        Icon(
                            painterResource(id = R.drawable.ic_visibility),
                            null
                        )
                    }
                } else {
                    IconButton(onClick = { showPassword = true }) {
                        Icon(
                            painterResource(id = R.drawable.ic_visibility_off),
                            null
                        )
                    }
                }
            }
        )
        if (errorMessage != null) {
            Text(
                text = errorMessage, style = TextStyle(
                    Color.Red, 14.sp,
                    FontWeight.Normal
                ),
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}


@Preview(showSystemUi = true, device = "id:pixel_8_pro", showBackground = true)
@Composable
private fun TextFieldPreview() {
    PasswordTextField(
        remember {
            mutableStateOf("")
        }, null
    )
}