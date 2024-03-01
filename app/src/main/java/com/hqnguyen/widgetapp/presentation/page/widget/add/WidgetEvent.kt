package com.hqnguyen.widgetapp.presentation.page.widget.add

import android.net.Uri
import com.hqnguyen.widgetapp.data.model.WidgetInfo

sealed class WidgetEvent {
    data class ShowLoading(val isShow: Boolean) : WidgetEvent()
    data class SaveWidget(val widgetInfo: WidgetInfo,val cachePatch: String) : WidgetEvent()
    data class FetchInfoWidget(val id: Long) : WidgetEvent()
    object UpdateDefaultPhoto : WidgetEvent()
    data class UpdateTitle(val title: String) : WidgetEvent()
    data class UpdateSize(val size: Int) : WidgetEvent()
    data class UpdateDate(val date: Long) : WidgetEvent()
    data class UpdateBackground(val uri: Uri) : WidgetEvent()
    data class UpdateFontSize(val fontSize: Float) : WidgetEvent()
    data class UpdateColorText(val color: Int) : WidgetEvent()
}