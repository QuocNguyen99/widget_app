package com.hqnguyen.widgetapp.utils

fun Int.convertTimeToText(listTimeInterval: List<Int>): String {
    return if (listTimeInterval[this] == 0) {
        "Press left or right to change the image"
    } else {
        when (this) {
            1 -> "${listTimeInterval[this]} seconds"
            2 -> "${listTimeInterval[this] / 60} minutes"
            else -> "${listTimeInterval[this] / 3600} hour"
        }
    }
}