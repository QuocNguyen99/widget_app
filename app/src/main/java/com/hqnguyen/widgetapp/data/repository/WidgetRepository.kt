package com.hqnguyen.widgetapp.data.repository

import com.hqnguyen.widgetapp.data.dao.WidgetDAO
import com.hqnguyen.widgetapp.data.model.WidgetInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WidgetRepository @Inject constructor(private val widgetDAO: WidgetDAO) {
    fun getAllListEvent(): List<WidgetInfo> = widgetDAO.getAllWidget()
    fun addWidgetInfo(event: WidgetInfo) = widgetDAO.insertWidget(event)
    suspend fun getWidget(id: Long): WidgetInfo = widgetDAO.getWidget(id)
    fun deleted(id: Long) = widgetDAO.deleteWidget(id)
}