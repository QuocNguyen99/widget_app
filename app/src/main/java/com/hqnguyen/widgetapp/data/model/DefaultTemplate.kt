package com.hqnguyen.widgetapp.data.model

import androidx.annotation.DrawableRes
import com.hqnguyen.widgetapp.R

data class DefaultTemplate(
    val title: String = "",
    val date: String = "",
    @DrawableRes val backgroundId: Int = R.mipmap.bg_birthday,
    val type: Int = -1
)

enum class TypeTemplate(type: Int) {
    GYM(0),
    STUDY(1),
    BIRTHDAY(2),
    WEDDING_ANNIVERSARY(2),
    TODO(3);

    companion object {
        fun from(findValue: String): TypeTemplate =
            TypeTemplate.values().first { it.name == findValue }
    }
}