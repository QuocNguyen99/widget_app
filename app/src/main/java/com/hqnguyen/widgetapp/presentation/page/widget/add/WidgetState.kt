package com.hqnguyen.widgetapp.presentation.page.widget.add

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

data class WidgetState(
    var isShowLoading: Boolean = false,
    var title: String = "",
    var date: Long = System.currentTimeMillis(),
    var pathImage: String = "",
    var textSize: Int = 0,
    var fontSize: Int = 9,
    var textColor: Int = Color.Red.toArgb(),
)