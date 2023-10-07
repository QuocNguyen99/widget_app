package com.hqnguyen.widgetapp.presentation.page.widget.add

import com.hqnguyen.widgetapp.data.model.WidgetInfo

sealed class WidgetEvent {
    data class ShowLoading(val isShow: Boolean) : WidgetEvent()
    data class SaveWidget(val widgetInfo: WidgetInfo) : WidgetEvent()
    data class FetchInfoWidget(val id: Long) : WidgetEvent()
    object UpdateDefaultPhoto : WidgetEvent()
    data class UpdateTitle(val title: String) : WidgetEvent()
    data class UpdateDate(val data: Long) : WidgetEvent()
    data class UpdateBackground(val path: String) : WidgetEvent()
    data class UpdateFontSize(val fontSize: Int) : WidgetEvent()
    data class UpdateColorText(val color: Int) : WidgetEvent()
}