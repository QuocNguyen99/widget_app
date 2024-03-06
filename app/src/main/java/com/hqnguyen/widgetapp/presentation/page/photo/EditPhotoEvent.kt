package com.hqnguyen.widgetapp.presentation.page.photo

import android.net.Uri
import androidx.compose.ui.graphics.Color
import com.hqnguyen.widgetapp.presentation.page.widget.add.WidgetEvent

sealed class EditPhotoEvent {
    data class UpdateSize(val size: Int) : EditPhotoEvent()
    data class UpdatePhoto(val path: Uri) : EditPhotoEvent()
    data class UpdateCropType(val type: Int) : EditPhotoEvent()
    data class UpdateBorderColor(val color: Color) : EditPhotoEvent()
}