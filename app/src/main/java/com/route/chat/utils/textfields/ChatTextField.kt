package com.route.chat.utils.textfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
fun ChatTextField(state: MutableState<String>) {

    OutlinedTextField(
        value = state.value,
        onValueChange = { newText ->
            state.value = newText
        },
        shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 20.dp),
        maxLines = 3,
        placeholder = { Text(text = stringResource(R.string.type_a_message)) },
        modifier = Modifier
            .fillMaxWidth(0.8f),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = mainBlue,
            unfocusedIndicatorColor = Color.Gray,
            unfocusedPlaceholderColor = Color.LightGray,
            focusedPlaceholderColor = Color.LightGray,
            cursorColor = Color.Gray,
            focusedTrailingIconColor = mainBlue,
            unfocusedTrailingIconColor = mainBlue,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Default
        )
    )
}

@Preview(showSystemUi = true, device = "id:pixel_8_pro", showBackground = true)
@Composable
private fun ChatTextFieldPreview() {
    ChatTextField(
        remember { mutableStateOf("") }
    )
}
