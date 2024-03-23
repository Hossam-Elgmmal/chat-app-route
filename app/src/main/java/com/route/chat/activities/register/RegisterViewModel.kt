package com.route.chat.activities.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    val username = mutableStateOf("")
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val errorEmail = mutableStateOf<String?>(null)
    val errorPassword = mutableStateOf<String?>(null)
}