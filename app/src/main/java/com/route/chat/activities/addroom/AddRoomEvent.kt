package com.route.chat.activities.addroom

sealed interface AddRoomEvent {

    data object Idle : AddRoomEvent
    data object NavigateBack : AddRoomEvent

}