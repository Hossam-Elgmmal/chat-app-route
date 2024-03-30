package com.route.chat.model

data class Room(
    val name: String? = "",
    val description: String? = "",
    val categoryId: String? = "",
) {
    companion object {
        const val COLLECTION_NAME = "Rooms"
    }
}
