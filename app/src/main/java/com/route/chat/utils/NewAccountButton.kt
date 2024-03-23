package com.route.chat.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.chat.R

@Composable
fun NewAccountButton(onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            Color.Transparent,
            Color.DarkGray,
            Color.DarkGray,
            Color.LightGray
        )
    ) {
        Text(text = stringResource(R.string.create_new_account))
    }
}


@Preview
@Composable
fun NewAccountButtonPreview() {
    NewAccountButton {}
}