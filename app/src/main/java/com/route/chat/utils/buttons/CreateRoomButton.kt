package com.route.chat.utils.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.chat.R
import com.route.chat.ui.theme.mainBlue

@Composable
fun CreateRoomButton(onClick: () -> Unit) {

    Button(
        onClick = onClick,
        colors = ButtonColors(mainBlue, Color.White, Color.DarkGray, Color.LightGray),
        shape = CircleShape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        elevation = ButtonDefaults.buttonElevation(8.dp),
        contentPadding = PaddingValues(32.dp, 16.dp)
    ) {
        Text(text = stringResource(id = R.string.create), fontWeight = FontWeight.Medium)

    }
}

@Preview(device = "id:pixel_8_pro", showSystemUi = true, showBackground = true)
@Composable
private fun CreateRoomButtonPreview() {
    CreateRoomButton {}

}