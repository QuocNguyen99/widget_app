package com.hqnguyen.widgetapp.presentation.page.photo

data class EditPhotoState(
    val path: String = "",
    val size: Int = 0,
    val positionX: Int = -1,
    val positionY: Int = -1,
    val width: Int = 0,
    val height: Int = 0,
)