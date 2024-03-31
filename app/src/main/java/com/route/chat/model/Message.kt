package com.route.chat.model

data class Message(
    val content: String = "",
    val senderId: String = "",
    val senderName: String = "",
    val dateTime: Long = 0,
    val roomId: String = ""
) {
    companion object {

        const val COLLECTION_NAME = "Messages"
    }
}
