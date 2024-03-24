package com.hqnguyen.widgetapp.presentation.page.photo

import android.net.Uri
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.hqnguyen.widgetapp.presentation.page.widget.add.WidgetEvent

sealed class EditPhotoEvent {
    data class UpdateSize(val size: Int) : EditPhotoEvent()
    data class UpdatePhoto
        (
        val listPath: List<Uri>,
        val isCrop: Boolean = false,
        val position: Int = -1
    ) :
        EditPhotoEvent()

    data class UpdateCropType(val type: Int) : EditPhotoEvent()
    data class UpdateBorderColor(val borderPosition: Int) : EditPhotoEvent()
    data class UpdateCorner(val cornerSize: Dp) : EditPhotoEvent()
    data class UpdateShape(val index: Int) : EditPhotoEvent()
    data class UpdateTimeInterval(val index: Int) : EditPhotoEvent()
    data object AddWidget : EditPhotoEvent()
}