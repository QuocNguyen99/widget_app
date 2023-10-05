package com.hqnguyen.widgetapp.data.model

data class Widget(
    var id: String = "",
    var size: Int? = null,
    var date: Long? = null,
    var colorText: Int? = null,
    var sizeText: Int? = null,
    var imagePath: String? = null,
    var fontText: String? = null,
)