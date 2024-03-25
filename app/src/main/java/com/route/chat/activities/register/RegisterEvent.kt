package com.route.chat.activities.register

import com.route.chat.model.ChatUser

sealed interface RegisterEvent {

    data object Idle : RegisterEvent
    data class NavigateToHome(val user: ChatUser) : RegisterEvent


}