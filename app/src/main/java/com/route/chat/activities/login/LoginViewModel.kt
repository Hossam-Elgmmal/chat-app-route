package com.route.chat.activities.login

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.route.chat.firebase.FirebaseUtils
import com.route.chat.model.ChatUser

class LoginViewModel : ViewModel() {

    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val errorEmail = mutableStateOf<String?>(null)
    val errorPassword = mutableStateOf<String?>(null)
    val isLoading = mutableStateOf(false)

    val loginEvent = mutableStateOf<LoginEvent>(LoginEvent.Idle)
    private val auth = Firebase.auth
    val loginError = mutableStateOf("")

    fun navigateToRegister() {
        loginEvent.value = LoginEvent.NavigateToRegister
    }

    private fun navigateToHome(user: ChatUser) {
        loginEvent.value = LoginEvent.NavigateToHome(user)
    }

    fun login() {
        if (validateFields()) {

            isLoading.value = true
            auth.signInWithEmailAndPassword(email.value, password.value)
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.e("TAG", "login: Error occurred ${task.exception?.message}")
                        isLoading.value = false
                        loginError.value = task.exception?.message
                            ?: "something went wrong, please try again later."
                        return@addOnCompleteListener
                    }
                    val uid = task.result.user?.uid
                    getUserFromFirestore(uid!!)

                    isLoading.value = false
                }
        }
    }

    private fun getUserFromFirestore(uid: String) {
        FirebaseUtils.getUser(
            uid,
            onSuccessListener = { docSnapshot ->
                val user = docSnapshot.toObject(ChatUser::class.java)
                navigateToHome(user!!)

            },
            onFailureListener = {
                loginError.value = it.message
                    ?: "something went wrong, please try again later."
                Log.e("TAG", "login: Error occurred ${it.message}")
            })
    }

    private fun validateFields(): Boolean {

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
}