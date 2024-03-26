package com.route.chat.activities.splash

import com.route.chat.model.ChatUser

sealed interface SplashEvent {
    data object Idle : SplashEvent
    data class NavigateToHome(val user: ChatUser) : SplashEvent
    data object NavigateToLogin : SplashEvent


}