package com.route.chat.activities.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class SplashViewModel: ViewModel() {

    val event = mutableStateOf<SplashEvent>(SplashEvent.Idle)

    fun navigateToHome(){
        event.value = SplashEvent.NavigateToHome
    }
    fun navigateToLogin(){
        event.value = SplashEvent.NavigateToLogin
    }

    fun navigate() {
        navigateToLogin()
    }



}