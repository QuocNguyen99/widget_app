package com.hqnguyen.widgetapp.presentation.page.widget.add

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hqnguyen.widgetapp.data.repository.WidgetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class WidgetViewModel @Inject constructor(private val widgetRepository: WidgetRepository) : ViewModel() {
    companion object {
        private val TAG = WidgetViewModel::class.java.name
    }

    private val mutableState = MutableStateFlow(WidgetState())
    val state: StateFlow<WidgetState> = mutableState.asStateFlow()

    fun handleEvents(event: WidgetEvent) {
        when (event) {
            is WidgetEvent.ShowLoading -> {}
            is WidgetEvent.FetchInfoWidget -> getEventWithId(event.id)
            is WidgetEvent.UpdateDefaultPhoto -> updateDefaultPhoto()
            is WidgetEvent.UpdateBackground -> changePhoto()
            is WidgetEvent.UpdateTitle -> updateTitle()
            is WidgetEvent.UpdateColorText -> updateTextColor()
            is WidgetEvent.UpdateDate -> updateDate()
            is WidgetEvent.UpdateFontSize -> updateFontSize()
            is WidgetEvent.SaveWidget -> saveWidget()
            else -> Unit
        }
    }

    private fun getEventWithId(id: Long) {
        viewModelScope.launch {
            try {
                val widgetInfo = widgetRepository.getWidget(id)
                mutableState.emit(
                    mutableState.value.copy(
                        title = widgetInfo.title,
                        textSize = widgetInfo.size,
                        date = widgetInfo.date,
                        textColor = Color.BLACK,
                        pathImage = widgetInfo.imagePath
                    )
                )
            } catch (ex: Exception) {
                Log.e(TAG, "getEventWithId error: ${ex.message}")
            }
        }
    }

    private fun updateDefaultPhoto(){}
    private fun changePhoto(){}
    private fun updateTitle(){}
    private fun updateTextSize(){}
    private fun updateFontSize(){}
    private fun updateTextColor(){}
    private fun updateDate(){}
    private fun saveWidget(){}
}