package com.hqnguyen.widgetapp.presentation.page.widget.add

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.hqnguyen.widgetapp.R

data class WidgetState(
    var isShowLoading: Boolean = false,
    var title: String = "Who's your birthday?",
    var date: Long = System.currentTimeMillis(),
    var pathImage: Uri? = null,
    @DrawableRes var defaultImage: Int? = R.mipmap.bg_wedding,
    var textSize: Float = 9f,
    var sizeCard: Int = 0,
    var textColor: Int = Color.White.toArgb(),
)