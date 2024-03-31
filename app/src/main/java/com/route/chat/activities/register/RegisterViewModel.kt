package com.route.chat.activities.register

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.route.chat.firebase.FirebaseUtils
import com.route.chat.model.ChatUser
import com.route.chat.model.DataUtils

class RegisterViewModel : ViewModel() {

    val username = mutableStateOf("")
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val errorName = mutableStateOf<String?>(null)
    val errorEmail = mutableStateOf<String?>(null)
    val errorPassword = mutableStateOf<String?>(null)
    private val auth = Firebase.auth
    val event = mutableStateOf<RegisterEvent>(RegisterEvent.Idle)
    val isLoading = mutableStateOf(false)
    val registerError = mutableStateOf("")


    fun register() {
        if (validateFields()) {
            isLoading.value = true
            auth.createUserWithEmailAndPassword(email.value, password.value)
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.e("TAG", "register: Error occurred ${task.exception?.message}")
                        isLoading.value = false
                        registerError.value = task.exception?.message
                            ?: "something went wrong, please try again later."
                        return@addOnCompleteListener
                    }
                    val uid = task.result?.user?.uid
                    addUserToFirestore(uid!!)
                    isLoading.value = false
                }
        }
    }

    private fun addUserToFirestore(uid: String) {
        val newUser = ChatUser(username.value, email.value, uid)
        FirebaseUtils.addUser(
            newUser,
            onSuccessListener = {
                DataUtils.chatUser = newUser
                navigateToHome(newUser)
            },
            onFailureListener = {
                registerError.value = it.message
                    ?: "something went wrong, please try again later."
            }
        )
    }

    private fun validateFields(): Boolean {
        if (username.value.trim().isEmpty()) {
            errorName.value = "required"
            return false
        } else {
            errorName.value = null
        }

        if (email.value.trim().isEmpty()) {
            errorEmail.value = "required"
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            errorEmail.value = "enter a valid email"
            return false
        } else {
            errorEmail.value = null
        }

        if (password.value.trim().isEmpty()) {
            errorPassword.value = "required"
            return false
        } else if (password.value.trim().length < 6) {
            errorPassword.value = "password cannot be less than 6 characters "

            return false
        } else {
            errorPassword.value = null
        }
        return true
    }

    private fun navigateToHome(user: ChatUser) {
        event.value = RegisterEvent.NavigateToHome(user)
    }
}