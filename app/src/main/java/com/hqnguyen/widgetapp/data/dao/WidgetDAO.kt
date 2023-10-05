package com.hqnguyen.widgetapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hqnguyen.widgetapp.data.entity.WidgetEntity
import com.hqnguyen.widgetapp.data.model.Widget
import kotlinx.coroutines.flow.Flow

@Dao
interface WidgetDAO {
    @Query("SELECT * FROM widget")
    fun getAllWidget(): Flow<List<Widget>>

    @Insert
    fun insertWidget(widget: Widget)

    @Delete
    fun deleteWidget(id: Long)
}