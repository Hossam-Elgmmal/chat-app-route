package com.route.chat.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatUser(
    val name: String? = null,
    val email: String? = null,
    val uid: String? = null,

    ) : Parcelable {
    companion object {

        const val COLLECTION_NAME = "USERS"
    }
}
