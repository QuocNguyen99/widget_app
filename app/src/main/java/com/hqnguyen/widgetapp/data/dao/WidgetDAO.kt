package com.hqnguyen.widgetapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hqnguyen.widgetapp.data.entity.WidgetEntity
import com.hqnguyen.widgetapp.data.model.WidgetInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface WidgetDAO {
    @Query("SELECT * FROM widget")
    fun getAllWidget(): Flow<List<WidgetEntity>>

    @Insert
    suspend fun insertWidget(widget: WidgetEntity)

    @Query("SELECT * FROM widget WHERE id = :id")
    suspend fun getWidget(id: Long): WidgetInfo

    @Query("DELETE FROM widget WHERE id = :id")
    fun deleteWidget(id: Long)
}