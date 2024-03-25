package com.route.chat.model

data class ChatUser(
    val name: String? = null,
    val email: String? = null,
    val uid: String? = null,

    ) {
    companion object {

        const val COLLECTION_NAME = "USERS"
    }
}
