package com.route.chat.activities.home

import com.route.chat.model.Room

sealed interface HomeEvent {

    data object Idle : HomeEvent
    data object NavigateToAddRoom : HomeEvent
    data class NavigateToChatRoom(val room: Room) : HomeEvent

}