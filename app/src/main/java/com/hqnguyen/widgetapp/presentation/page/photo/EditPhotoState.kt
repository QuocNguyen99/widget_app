package com.hqnguyen.widgetapp.presentation.page.photo

import android.net.Uri
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class EditPhotoState(
    val listPath: List<Uri>? = null,
    val size: Int = 2,
    val positionX: Int = -1,
    val positionY: Int = -1,
    val width: Int = 0,
    val height: Int = 0,
    val borderColor: Int = -1,
    val cropType: Int = 0,
    val cornerSize: Dp = 16.dp,
    val shapeIndex:Int = 0
)