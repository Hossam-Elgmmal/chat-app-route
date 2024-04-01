package com.route.chat.model

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

    fun getFormattedTime(): String {

        val date = Date(dateTime)
        val formatter = SimpleDateFormat.getPatternInstance("hh:mm a", Locale.getDefault())

        return formatter.format(date)
    }
}
