package com.route.chat.utils.bottombars

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.chat.R
import com.route.chat.ui.theme.mainBlue
import com.route.chat.utils.textfields.ChatTextField

@Composable
fun ChatRoomBottomBar(state: MutableState<String>, onSendClick: () -> Unit) {
    BottomAppBar(
        contentPadding = PaddingValues(16.dp, 4.dp),
        windowInsets = WindowInsets.navigationBars,
        containerColor = Color.Transparent
    ) {
        ChatTextField(state = state)
        Spacer(modifier = Modifier.weight(1f))
        TextButton(
            onClick = { onSendClick() },
            shape = CircleShape
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_send),
                contentDescription = stringResource(R.string.send),
                tint = mainBlue
            )
        }
    }
}

@Preview
@Composable
private fun ChatRoomBottomBarPreview() {
    ChatRoomBottomBar(remember { mutableStateOf("") }, {})
}