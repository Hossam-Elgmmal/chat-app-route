package com.route.chat.activities.home

sealed interface HomeEvent {

    data object Idle : HomeEvent
    data object NavigateToAddRoom : HomeEvent

}