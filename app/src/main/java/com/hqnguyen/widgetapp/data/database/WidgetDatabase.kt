package com.hqnguyen.widgetapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hqnguyen.widgetapp.data.dao.WidgetDAO
import com.hqnguyen.widgetapp.data.entity.WidgetEntity


@Database(entities = [WidgetEntity::class], version = 1)
abstract class WidgetDatabase : RoomDatabase() {
    abstract fun widgetDAO(): WidgetDAO
}