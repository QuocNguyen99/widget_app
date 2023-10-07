package com.hqnguyen.widgetapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hqnguyen.widgetapp.data.model.WidgetInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface WidgetDAO {
    @Query("SELECT * FROM widget")
    fun getAllWidget(): List<WidgetInfo>

    @Insert
    fun insertWidget(widget: WidgetInfo)

    @Query("SELECT * FROM widget WHERE id = :id")
    suspend fun getWidget(id: Long): WidgetInfo

    @Delete
    fun deleteWidget(id: Long)
}