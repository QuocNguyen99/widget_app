package com.hqnguyen.widgetapp.data.model

import android.graphics.Color

data class WidgetInfo(
    var id: String,
    var title: String="",
    var size: Int = 0,
    var date: Long = System.currentTimeMillis(),
    var colorText: Int = Color.BLACK,
    var sizeText: Int? = 9,
    var imagePath: String = "",
    var fontText: String = "",
)