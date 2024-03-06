package com.hqnguyen.widgetapp.presentation.page.photo

import android.net.Uri
import androidx.compose.ui.graphics.Color

data class EditPhotoState(
    val path: Uri? = null,
    val size: Int = 2,
    val positionX: Int = -1,
    val positionY: Int = -1,
    val width: Int = 0,
    val height: Int = 0,
    val borderColor: Color? = null,
    val cropType: Int = 0
)