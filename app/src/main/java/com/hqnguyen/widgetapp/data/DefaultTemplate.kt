package com.hqnguyen.widgetapp.data

import androidx.annotation.DrawableRes
import com.hqnguyen.widgetapp.R

data class DefaultTemplate(
    val title: String = "",
    val date: String = "",
    @DrawableRes val backgroundId: Int = R.mipmap.bg_birthday,
    val type: TypeTemplate = TypeTemplate.TODO
)

enum class TypeTemplate(type: String) {
    GYM("gym"),
    STUDY("study"),
    BIRTHDAY("brithday"),
    WEDDING_ANNIVERSARY("wedding_anniversary"),
    TODO("todo"),
    PLAN("plan")
}