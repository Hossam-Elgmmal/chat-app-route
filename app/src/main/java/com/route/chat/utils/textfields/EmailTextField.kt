package com.route.chat.utils.textfields

import android.util.Patterns
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.chat.R
import com.route.chat.ui.theme.mainBlue

@Composable
fun EmailTextField(state: MutableState<String>, errorMessage: String?) {

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
            isError = errorMessage != null,
            placeholder = { Text(text = stringResource(id = R.string.email)) },
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = mainBlue,
                unfocusedIndicatorColor = Color.Gray,
                focusedPlaceholderColor = Color.LightGray,
                unfocusedPlaceholderColor = Color.LightGray,
                cursorColor = Color.Gray,
                focusedTrailingIconColor = mainBlue,
                unfocusedTrailingIconColor = mainBlue,
                errorContainerColor = Color.White,
                errorIndicatorColor = Color.Red,
                errorLabelColor = Color.Red,
                errorTrailingIconColor = Color.Red,
                errorCursorColor = Color.Gray,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            trailingIcon = {
                if (errorMessage != null) {
                    IconButton(onClick = { }) {
                        Icon(
                            painterResource(id = R.drawable.ic_error),
                            null
                        )
                    }
                } else if (Patterns.EMAIL_ADDRESS.matcher(state.value).matches()) {
                    IconButton(onClick = { }) {
                        Icon(
                            painterResource(id = R.drawable.ic_check),
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
    EmailTextField(
        remember {
            mutableStateOf("")
        }, null
    )
}