package com.route.chat.activities.addroom

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.route.chat.firebase.FirebaseUtils
import com.route.chat.model.Category
import com.route.chat.model.Room

class AddRoomViewModel : ViewModel() {
    val roomName = mutableStateOf("")
    val roomNameError = mutableStateOf<String?>(null)
    val roomDescription = mutableStateOf("")
    val roomDescriptionError = mutableStateOf<String?>(null)
    val isMenuExpanded = mutableStateOf(false)
    val categoriesList = Category.getCategories()
    val selectedCategoryItem = mutableStateOf(categoriesList[0])
    val isLoading = mutableStateOf(false)
    val event = mutableStateOf<AddRoomEvent>(AddRoomEvent.Idle)

    fun addRoomToFirestore() {
        if (validateFields()) {
            isLoading.value = true

            val room = Room(
                roomName.value,
                roomDescription.value,
                selectedCategoryItem.value.id
            )

            FirebaseUtils.addRoom(
                room,
                onSuccessListener = {
                    isLoading.value = false
                    navigateBack()
                },
                onFailureListener = {

                    isLoading.value = false
                    Log.e("TAG", "add room: Error occurred ${it.message}")
                })
        }
    }

    private fun navigateBack() {
        event.value = AddRoomEvent.NavigateBack
    }

    private fun validateFields(): Boolean {

        if (roomName.value.trim().isEmpty()) {
            roomNameError.value = "required"
            return false
        } else {
            roomNameError.value = null
        }
        if (roomDescription.value.trim().isEmpty()) {
            roomDescriptionError.value = "required"
            return false
        } else {
            roomDescriptionError.value = null
        }

        return true
    }
}
