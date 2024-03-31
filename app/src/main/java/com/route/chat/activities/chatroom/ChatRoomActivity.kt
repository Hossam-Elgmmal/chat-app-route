package com.route.chat.activities.chatroom

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.chat.R
import com.route.chat.model.Constants
import com.route.chat.model.Room
import com.route.chat.ui.theme.ChatAppRouteTheme
import com.route.chat.utils.appbars.ChatRoomAppBar
import com.route.chat.utils.bottombars.ChatRoomBottomBar

class ChatRoomActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val room = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constants.ROOM_KEY, Room::class.java)
        } else {
            intent.getParcelableExtra(Constants.ROOM_KEY) as Room?
        }
        enableEdgeToEdge()
        setContent {
            ChatAppRouteTheme {
                ChatRoom(room = room!!) {
                    finish()
                }
            }
        }
    }
}

@Composable
fun ChatRoom(vm: ChatRoomViewModel = viewModel(), room: Room, onFinish: () -> Unit) {

    LaunchedEffect(key1 = Unit) {
        vm.room = room
    }
    Scaffold(
        modifier = Modifier
            .imePadding()
            .fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {

            ChatRoomAppBar(room = room) {
                onFinish()
            }
        },
        bottomBar = {
            ChatRoomBottomBar(
                vm.messageContent
            ) {
                vm.sendMessage()
            }
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_background),
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )

            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
            ) {

            }

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ChatRoomPreview() {
    ChatRoom(viewModel(), Room("name")) {}
}