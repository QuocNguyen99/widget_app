package com.hqnguyen.widgetapp.presentation.page.home

sealed interface HomeEvent {
    data class CardClick(val type: String) : HomeEvent
    object GetFirstData : HomeEvent
    object OnBackPress : HomeEvent
}