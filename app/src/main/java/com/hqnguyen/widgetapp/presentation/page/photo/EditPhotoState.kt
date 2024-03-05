package com.hqnguyen.widgetapp.presentation.page.photo

import android.net.Uri

data class EditPhotoState(
    val path: Uri? = null,
    val size: Int = 2,
    val positionX: Int = -1,
    val positionY: Int = -1,
    val width: Int = 0,
    val height: Int = 0,
    val cropType: Int = 0
)