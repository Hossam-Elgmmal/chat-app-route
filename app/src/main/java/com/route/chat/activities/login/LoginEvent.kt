package com.route.chat.activities.login


sealed interface LoginEvent {
    data object Idle : LoginEvent
    data object NavigateToHome : LoginEvent
    data object NavigateToRegister : LoginEvent
}