package com.hqnguyen.widgetapp.presentation.page.photo

import android.net.Uri
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hqnguyen.widgetapp.presentation.page.widget.add.WidgetViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditPhotoViewModel @Inject constructor() : ViewModel() {
    companion object {
        private val TAG = WidgetViewModel::class.java.name
    }

    private val mutableState = MutableStateFlow(EditPhotoState())
    val state: StateFlow<EditPhotoState> = mutableState.asStateFlow()

    fun handleEvents(event: EditPhotoEvent) {
        when (event) {
            is EditPhotoEvent.UpdatePhoto -> updatePhoto(event.path)
            is EditPhotoEvent.UpdateSize -> updateSize(event.size)
            is EditPhotoEvent.UpdateCropType -> updateCropType(event.type)
            is EditPhotoEvent.UpdateBorderColor -> updateBorderColor(event.borderPosition)
            is EditPhotoEvent.UpdateCorner -> updateCorner(event.cornerSize)
            is EditPhotoEvent.UpdateShape -> updateShape(event.index)
        }
    }

    private fun updateShape(index: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "updateShape index $index")
            mutableState.emit(
                mutableState.value.copy(
                    shapeIndex = index
                )
            )
        }
    }

    private fun updateCorner(cornerSize: Dp) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "updateCorner cornerSize $cornerSize")
            mutableState.emit(
                mutableState.value.copy(
                    cornerSize = cornerSize
                )
            )
        }
    }

    private fun updateBorderColor(borderPosition: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "updateBorderColor borderPosition $borderPosition")
            mutableState.emit(
                mutableState.value.copy(
                    borderColor = borderPosition
                )
            )
        }
    }

    private fun updateCropType(type: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "updateCropType type $type")
            mutableState.emit(
                mutableState.value.copy(
                    cropType = type
                )
            )
        }
    }

    private fun updateSize(size: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "updateSize size $size")
            mutableState.emit(
                mutableState.value.copy(
                    size = size
                )
            )
        }
    }

    private fun updatePhoto(path: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "updatePhoto path $path")
            mutableState.emit(
                mutableState.value.copy(
                    path = path
                )
            )
        }
    }
}