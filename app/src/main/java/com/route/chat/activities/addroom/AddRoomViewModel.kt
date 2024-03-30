package com.route.chat.activities.addroom

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.route.chat.model.Category

class AddRoomViewModel : ViewModel() {
    val roomName = mutableStateOf("")
    val roomNameError = mutableStateOf<String?>(null)
    val roomDescription = mutableStateOf("")
    val roomDescriptionError = mutableStateOf<String?>(null)
    val isMenuExpanded = mutableStateOf(false)
    val categoriesList = Category.getCategories()
    val selectedCategoryItem = mutableStateOf(categoriesList[0])
}