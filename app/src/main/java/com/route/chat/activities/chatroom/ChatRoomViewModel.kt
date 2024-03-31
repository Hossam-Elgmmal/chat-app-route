package com.route.chat.activities.chatroom

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.route.chat.firebase.FirebaseUtils
import com.route.chat.model.DataUtils
import com.route.chat.model.Message
import com.route.chat.model.Room
import java.util.Date

class ChatRoomViewModel : ViewModel() {
    val messageContent = mutableStateOf("")
    var room: Room = Room()

    fun sendMessage() {

        if (messageContent.value.trim().isEmpty()) return

        val message = Message(
            content = messageContent.value.trim(),
            senderName = DataUtils.chatUser?.name!!,
            senderId = DataUtils.chatUser?.uid!!,
            dateTime = Date().time,
            roomId = room.roomId
        )

        FirebaseUtils.addMessage(
            message,
            onSuccessListener = {
                messageContent.value = ""


            },
            onFailureListener = {

                Log.e("TAG", "sendMessage: ${it.message}")

            })

    }

}
