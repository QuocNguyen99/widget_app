package com.hqnguyen.widgetapp.presentation.page.photo

import com.hqnguyen.widgetapp.presentation.page.widget.add.WidgetEvent

sealed class EditPhotoEvent {
    data class UpdateSize(val size: Int) : EditPhotoEvent()
    data class UpdatePhoto(val path: String) : EditPhotoEvent()
}