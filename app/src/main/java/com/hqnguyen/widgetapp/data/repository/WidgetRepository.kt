package com.hqnguyen.widgetapp.data.repository

import com.hqnguyen.widgetapp.data.dao.WidgetDAO
import com.hqnguyen.widgetapp.data.entity.WidgetEntity
import com.hqnguyen.widgetapp.data.model.WidgetInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class WidgetRepository @Inject constructor(private val widgetDAO: WidgetDAO) {
    fun getAllListEvent(): Flow<List<WidgetEntity>> = widgetDAO.getAllWidget()
    suspend fun addWidgetInfo(event: WidgetEntity) = widgetDAO.insertWidget(event)
    suspend fun getWidget(id: Long) = widgetDAO.getWidget(id)
    fun deleted(id: Long) = widgetDAO.deleteWidget(id)
}