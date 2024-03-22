package com.route.chat.activities.splash

sealed interface SplashEvent {
    data object Idle : SplashEvent
    data object NavigateToHome : SplashEvent
    data object NavigateToLogin : SplashEvent


}