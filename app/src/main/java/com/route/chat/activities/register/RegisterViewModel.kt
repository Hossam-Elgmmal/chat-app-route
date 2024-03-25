package com.route.chat.activities.register

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.route.chat.firebase.FirebaseUtils
import com.route.chat.model.ChatUser

class RegisterViewModel : ViewModel() {

    val username = mutableStateOf("")
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val errorName = mutableStateOf<String?>(null)
    val errorEmail = mutableStateOf<String?>(null)
    val errorPassword = mutableStateOf<String?>(null)
    val auth = Firebase.auth
    val event = mutableStateOf<RegisterEvent>(RegisterEvent.Idle)


    fun register() {
        if (validateFields()) {
            auth.createUserWithEmailAndPassword(email.value, password.value)
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.e("TAG", "register: Error occurred ${task.exception?.message}")
                        return@addOnCompleteListener
                    }
                    val uid = task.result?.user?.uid
                    addUserToFirestore(uid!!)

                }
        }
    }

    fun addUserToFirestore(uid: String) {
        val newUser = ChatUser(username.value, email.value, uid)
        FirebaseUtils.addUser(
            newUser,
            onSuccessListener = {
                navigateToHome(newUser)
            },
            onFailureListener = {

            }
        )
    }

    fun validateFields(): Boolean {
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

    fun navigateToHome(user: ChatUser) {
        event.value = RegisterEvent.NavigateToHome(user)
    }
}