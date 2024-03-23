package com.route.chat.activities.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val errorEmail = mutableStateOf<String?>(null)
    val errorPassword = mutableStateOf<String?>(null)

    val loginEvent = mutableStateOf<LoginEvent>(LoginEvent.Idle)

    fun navigateToRegister() {
        loginEvent.value = LoginEvent.NavigateToRegister
    }

    fun navigateToHome() {
        loginEvent.value = LoginEvent.NavigateToHome
    }
}