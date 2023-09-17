package com.hqnguyen.widgetapp.data

import androidx.annotation.DrawableRes
import com.hqnguyen.widgetapp.R

data class DefaultTemplate(
    val title: String = "",
    val date: String = "",
    @DrawableRes val backgroundId: Int = R.mipmap.bg_birthday,
    val type: String = ""
)

enum class TypeTemplate(type: String) {
    GYM("GYM"),
    STUDY("STUDY"),
    BIRTHDAY("BIRTHDAY"),
    WEDDING_ANNIVERSARY("WEDDING_ANNIVERSARY"),
    TODO("TODO");

    companion object {
        fun from(findValue: String): TypeTemplate =
            TypeTemplate.values().first { it.name == findValue }
    }
}