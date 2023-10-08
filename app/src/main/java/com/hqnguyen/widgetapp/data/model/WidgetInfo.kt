package com.hqnguyen.widgetapp.data.model

import android.graphics.Color
import android.net.Uri

data class WidgetInfo(
    var id: String,
    var title: String = "",
    var size: Int = 0,
    var date: Long = System.currentTimeMillis(),
    var colorText: Int = Color.BLACK,
    var sizeText: Float = 9f,
    var imagePath: String? = null,
    var defaultImage: Int? = null
)