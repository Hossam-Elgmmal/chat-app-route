package com.route.chat.activities.login

import com.route.chat.model.ChatUser

sealed interface LoginEvent {
    data object Idle : LoginEvent
    data class NavigateToHome(val user: ChatUser) : LoginEvent
    data object NavigateToRegister : LoginEvent
}