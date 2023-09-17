package com.hqnguyen.widgetapp.ui.page.home

import com.hqnguyen.widgetapp.data.DefaultTemplate

sealed interface HomeEvent {
    data class CardClick(val type: String) : HomeEvent
    object GetFirstData : HomeEvent
    object OnBackPress : HomeEvent
}