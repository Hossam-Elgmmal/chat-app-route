package com.route.chat.utils.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.chat.R

@Composable
fun CreateAccountButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonColors(Color.White, Color.DarkGray, Color.DarkGray, Color.LightGray),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        elevation = ButtonDefaults.buttonElevation(4.dp),
        contentPadding = PaddingValues(32.dp, 16.dp)
    ) {
        Text(text = stringResource(id = R.string.create_account), fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.weight(1f))
        Icon(painter = painterResource(id = R.drawable.ic_forward), contentDescription = null)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun CreateAccountButtonPreview() {
    CreateAccountButton {}
}