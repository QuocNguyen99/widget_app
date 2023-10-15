package com.hqnguyen.widgetapp.data.model

import android.graphics.Color
import android.net.Uri
import com.hqnguyen.widgetapp.data.entity.WidgetEntity

data class WidgetInfo(
    var id: String?,
    var title: String? = "",
    var size: Int? = 0,
    var date: Long? = System.currentTimeMillis(),
    var colorText: Int? = Color.BLACK,
    var sizeText: Float? = 9f,
    var imagePath: String? = null,
    var defaultImage: Int? = null,
    var fontText: String? = null,
) {
    fun toEntity(): WidgetEntity {
        return WidgetEntity(
            id = id ?: System.currentTimeMillis().toString(),
            title = title,
            size = size,
            date = date,
            colorText = colorText,
            sizeText = sizeText?.toInt(),
            imagePath = imagePath,
            fontText = fontText
        )
    }
}