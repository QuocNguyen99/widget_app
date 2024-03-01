package com.hqnguyen.widgetapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hqnguyen.widgetapp.data.model.WidgetInfo

@Entity(tableName = "widget")
data class WidgetEntity(
    @PrimaryKey
    var id: String = "",
    var title: String = "",
    var size: Int = -1,
    var date: Long = -1L,
    var colorText: Int = -1,
    var sizeText: Int = -1,
    var imagePath: String = "",
    var fontText: String = "",
    var defaultImage: Int = -1
) {
    fun toModel(): WidgetInfo {
        return WidgetInfo(
            id = id,
            title = title,
            size = size,
            date = date,
            colorText = colorText,
            sizeText = sizeText.toFloat(),
            imagePath = imagePath,
            fontText = fontText,
            defaultImage = defaultImage
        )
    }
}