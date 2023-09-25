package com.hqnguyen.widgetapp.presentation.page.home

import com.hqnguyen.widgetapp.data.DefaultTemplate

sealed interface HomeEvent {
    data class CardClick(val type: String) : HomeEvent
    object GetFirstData : HomeEvent
    object OnBackPress : HomeEvent
}