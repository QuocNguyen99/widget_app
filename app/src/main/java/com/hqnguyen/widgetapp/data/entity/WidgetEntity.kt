package com.hqnguyen.widgetapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "widget")
data class WidgetEntity(
    @PrimaryKey
    var id: String = "",
    var title: String = "",
    var size: Int? = null,
    var date: Long? = null,
    var colorText: Int? = null,
    var sizeText: Int? = null,
    var imagePath: String? = null,
    var fontText: String? = null,
)