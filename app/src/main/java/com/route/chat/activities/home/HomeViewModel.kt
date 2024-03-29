package com.route.chat.activities.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    val event = mutableStateOf<HomeEvent>(HomeEvent.Idle)

    fun navigateToAddRoom() {
        event.value = HomeEvent.NavigateToAddRoom
    }

    fun resetEvent() {
        event.value = HomeEvent.Idle
    }
}