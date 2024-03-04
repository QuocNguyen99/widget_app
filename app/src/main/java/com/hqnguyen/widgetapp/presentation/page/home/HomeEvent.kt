package com.hqnguyen.widgetapp.presentation.page.home

sealed interface HomeEvent {
    data class CardClick(val type: String) : HomeEvent
    data object GetFirstData : HomeEvent
    data object OnBackPress : HomeEvent
}