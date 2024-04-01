package com.route.chat.activities.chatroom

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentChange
import com.route.chat.firebase.FirebaseUtils
import com.route.chat.model.DataUtils
import com.route.chat.model.Message
import com.route.chat.model.Room
import java.util.Date

class ChatRoomViewModel : ViewModel() {
    val messageContent = mutableStateOf("")
    var room: Room = Room()
    val messagesList = mutableStateListOf<Message>()

    fun sendMessage() {

        if (messageContent.value.trim().isEmpty()) return

        val message = Message(
            content = messageContent.value.trim(),
            senderName = DataUtils.chatUser?.name!!,
            senderId = DataUtils.chatUser?.uid!!,
            dateTime = Date().time,
            roomId = room.id
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

    fun listenForMessages() {
        FirebaseUtils.getMessages(room.id) { snapShot, error ->

            if (error != null) {
                Log.e("TAG", "listenForMessages: ${error.message}")
                return@getMessages
            }
            val list = mutableListOf<Message>()
            for (dc in snapShot!!.documentChanges) {

                when (dc.type) {
                    DocumentChange.Type.ADDED -> {
                        list.add(dc.document.toObject(Message::class.java))
                    }

                    DocumentChange.Type.MODIFIED -> {}
                    DocumentChange.Type.REMOVED -> {}
                }
            }
            list.addAll(messagesList)
            messagesList.clear()
            messagesList.addAll(list)

        }
    }
}
