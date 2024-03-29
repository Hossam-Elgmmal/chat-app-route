package com.route.chat.activities.addroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.route.chat.ui.theme.ChatAppRouteTheme

class AddRoomActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppRouteTheme {

                AddRoomContent()
            }
        }
    }
}

@Composable
fun AddRoomContent() {

    //to do

}

@Preview
@Composable
private fun AddRoomContentPreview() {
    AddRoomContent()
}


