package com.route.chat.activities.home

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.route.chat.firebase.FirebaseUtils
import com.route.chat.model.Room

class HomeViewModel : ViewModel() {

    val event = mutableStateOf<HomeEvent>(HomeEvent.Idle)
    val isLoading = mutableStateOf(false)
    val roomsList = mutableStateListOf<Room>()

    fun navigateToAddRoom() {
        event.value = HomeEvent.NavigateToAddRoom
    }

    fun navigateToChatRoom(room: Room) {
        event.value = HomeEvent.NavigateToChatRoom(room)
    }
    fun resetEvent() {
        event.value = HomeEvent.Idle
    }

    fun getRoomsFromFirestore() {
        isLoading.value = true
        FirebaseUtils.getRooms(
            onSuccessListener = {

                roomsList.clear()
                val list = it.toObjects(Room::class.java)
                roomsList.addAll(list)

                isLoading.value = false
            },
            onFailureListener = {
                Log.e("TAG", "getRoomsFromFireBase: ${it.message}")
                isLoading.value = false
            }
        )

    }
}