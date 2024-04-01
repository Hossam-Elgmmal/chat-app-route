package com.route.chat.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    val name: String = "",
    val description: String = "",
    val categoryId: String = "",
    var id: String = "",
) : Parcelable {
    companion object {
        const val COLLECTION_NAME = "Rooms"
    }
}
