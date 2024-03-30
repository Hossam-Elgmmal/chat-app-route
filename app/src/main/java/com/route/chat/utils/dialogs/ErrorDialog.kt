package com.route.chat.utils.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import com.route.chat.R

@Composable
fun ErrorDialog(title: MutableState<String>) {
    if (title.value.trim().isNotEmpty()) {
        AlertDialog(
            onDismissRequest = { title.value = "" },
            confirmButton = {
                TextButton(onClick = {
                    title.value = ""
                }) {

                    Text(text = stringResource(R.string.ok))
                }
            },
            text = { Text(text = title.value) }

        )
    }
}