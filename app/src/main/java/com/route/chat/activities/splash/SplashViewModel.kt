package com.route.chat.activities.splash

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.route.chat.firebase.FirebaseUtils
import com.route.chat.model.ChatUser
import com.route.chat.model.DataUtils


class SplashViewModel : ViewModel() {

    val event = mutableStateOf<SplashEvent>(SplashEvent.Idle)

    private val auth = Firebase.auth


    private fun navigateToHome(user: ChatUser) {
        event.value = SplashEvent.NavigateToHome(user)
    }

    private fun navigateToLogin() {
        event.value = SplashEvent.NavigateToLogin
    }

    fun navigate() {
        if (auth.currentUser != null)
            auth.currentUser?.uid?.let {

                getUserFromFirestore(it)
            } ?: navigateToLogin()
        else
            navigateToLogin()
    }

    private fun getUserFromFirestore(uid: String) {
        FirebaseUtils.getUser(
            uid = uid,
            onSuccessListener = {
                val chatUser = it.toObject(ChatUser::class.java)
                DataUtils.chatUser = chatUser
                chatUser?.let { user ->
                    navigateToHome(user)
                } ?: navigateToLogin()

            },
            onFailureListener = {
                Log.e("TAG", "getUserFromFirestore: ${it.message}")
                navigateToLogin()
            }

        )
    }
}