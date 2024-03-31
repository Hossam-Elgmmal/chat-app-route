package com.route.chat.utils.appbars

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.route.chat.R
import com.route.chat.model.Room

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatRoomAppBar(room: Room, onNavigationClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = room.name,
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults
            .centerAlignedTopAppBarColors(
                Color.Transparent,
                titleContentColor = Color.White
            ),
        navigationIcon = {
            IconButton(onClick = {
                onNavigationClick()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = stringResource(R.string.back_to_home),
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = {

            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = stringResource(R.string.more_options),
                    tint = Color.White
                )
            }
        }
    )
}

@Preview
@Composable
private fun ChatRoomBarPreview() {
    ChatRoomAppBar(Room("name")) {

    }
}