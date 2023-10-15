package com.hqnguyen.widgetapp.presentation.page.event

import com.hqnguyen.widgetapp.data.model.WidgetInfo

data class EventState(
    val listEvent: MutableList<WidgetInfo> = mutableListOf()
)