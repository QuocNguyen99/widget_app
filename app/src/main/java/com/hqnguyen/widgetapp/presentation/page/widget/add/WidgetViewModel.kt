package com.hqnguyen.widgetapp.presentation.page.widget.add

import android.graphics.Color
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hqnguyen.widgetapp.R
import com.hqnguyen.widgetapp.data.repository.WidgetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class WidgetViewModel @Inject constructor(private val widgetRepository: WidgetRepository) :
    ViewModel() {
    companion object {
        private val TAG = WidgetViewModel::class.java.name
    }

    private val mutableState = MutableStateFlow(WidgetState())
    val state: StateFlow<WidgetState> = mutableState.asStateFlow()

    fun handleEvents(event: WidgetEvent) {
        Log.d(TAG, "handleEvents: $event")
        when (event) {
            is WidgetEvent.ShowLoading -> showLoading(event.isShow)
            is WidgetEvent.FetchInfoWidget -> getEventWithId(event.id)
            is WidgetEvent.UpdateDefaultPhoto -> updateDefaultPhoto()
            is WidgetEvent.UpdateBackground -> changePhoto(event.uri)
            is WidgetEvent.UpdateSize -> updateSize(event.size)
            is WidgetEvent.UpdateTitle -> updateTitle(event.title)
            is WidgetEvent.UpdateColorText -> updateTextColor(event.color)
            is WidgetEvent.UpdateDate -> updateDate(event.date)
            is WidgetEvent.UpdateFontSize -> updateFontSize(event.fontSize)
            is WidgetEvent.SaveWidget -> saveWidget()
        }
    }

    private fun showLoading(isShow: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            mutableState.emit(
                mutableState.value.copy(
                    isShowLoading = isShow
                )
            )
        }
    }

    private fun getEventWithId(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val widgetInfo = widgetRepository.getWidget(id)
                mutableState.emit(
                    mutableState.value.copy(
                        title = widgetInfo.title,
                        textSize = widgetInfo.sizeText,
                        date = widgetInfo.date,
                        textColor = Color.BLACK,
                        pathImage = if(widgetInfo.imagePath != null) widgetInfo.imagePath!!.toUri() else null
                    )
                )
            } catch (ex: Exception) {
                Log.e(TAG, "getEventWithId error: ${ex.message}")
            }
        }
    }

    private fun updateDefaultPhoto() {
        Log.d(TAG, "updateDefaultPhoto")
        viewModelScope.launch {
            mutableState.emit(
                mutableState.value.copy(
                    defaultImage = R.mipmap.bg_wedding,
                    pathImage = null
                )
            )
        }
    }

    private fun changePhoto(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            mutableState.emit(mutableState.value.copy(pathImage = uri, defaultImage = null))
        }
    }

    private fun updateTitle(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            mutableState.emit(mutableState.value.copy(title = title))
        }
    }

    private fun updateSize(size: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            mutableState.emit(mutableState.value.copy(sizeCard = size))
        }
    }

    private fun updateFontSize(size: Float) {
        viewModelScope.launch(Dispatchers.IO) {
            mutableState.emit(mutableState.value.copy(textSize = size))
        }
    }

    private fun updateTextColor(color: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            mutableState.emit(mutableState.value.copy(textColor = color))
        }
    }

    private fun updateDate(date: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            mutableState.emit(mutableState.value.copy(date = date))
        }
    }

    private fun saveWidget() {
        viewModelScope.launch {
            showLoading(true)
            try {
//                widgetRepository
            }catch (ex:Exception){

            }
        }
    }
}