package com.hqnguyen.widgetapp.presentation.page.event

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hqnguyen.widgetapp.data.repository.WidgetRepository
import com.hqnguyen.widgetapp.presentation.page.widget.add.WidgetState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(private val widgetRepository: WidgetRepository) : ViewModel() {

    companion object {
        private val TAG = EventViewModel.javaClass.name
    }

    private val mutableState = MutableStateFlow(EventState())
    val state: StateFlow<EventState> = widgetRepository.getAllListEvent().map { EventState(it.map { item -> item.toModel() }.toMutableList()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = EventState()
        )


//    private fun getAllEvents() {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                Log.d(TAG, "getAllEvents Entry")
//                widgetRepository.getAllListEvent().map {
//                    Log.d(TAG, "getAllEvents lenth: ${it.size}")
//                    mutableState.value.copy(listEvent = it.map { item -> item.toModel() }.toMutableList())
//                }.stateIn(
//                    scope = viewModelScope,
//                    started = SharingStarted.WhileSubscribed(5000),
//                    initialValue = EventState()
//                ).value
//                Log.d(TAG, "getAllEvents size: ${mutableState.value.listEvent.size}")
//            } catch (ex: Exception) {
//                Log.e(TAG, "getAllEvents: ${ex.message} ")
//            }
//        }
//    }
}