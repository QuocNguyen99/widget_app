package com.hqnguyen.widgetapp.data.model

import androidx.annotation.DrawableRes
import com.hqnguyen.widgetapp.R

data class DefaultTemplate(
    val title: String = "",
    val date: String = "",
    @DrawableRes val backgroundId: Int = R.mipmap.bg_birthday,
    val type: Int = -1,
    val size: Int = -1
)

enum class TypeTemplate {
    GYM,
    STUDY,
    BIRTHDAY,
    WEDDING_ANNIVERSARY,
    PHOTO,
    TODO;

    companion object {
        fun from(findValue: String): TypeTemplate =
            entries.first { it.name == findValue }
    }
}