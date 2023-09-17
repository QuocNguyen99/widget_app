package com.hqnguyen.widgetapp.data

import androidx.annotation.DrawableRes
import com.hqnguyen.widgetapp.R

data class DefaultTemplate(
    val title: String = "",
    val date: String = "",
    @DrawableRes val backgroundId: Int = R.mipmap.bg_birthday
)