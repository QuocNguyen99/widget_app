package com.hqnguyen.widgetapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.hqnguyen.widgetapp.data.dao.WidgetDAO
import com.hqnguyen.widgetapp.data.entity.WidgetEntity

@Database(entities = [WidgetEntity::class], version = 1)
abstract class WidgetDatabase : RoomDatabase() {
    abstract fun widgetDAO(): WidgetDAO

//    companion object {
//
//        @Volatile
//        private var instance: WidgetDatabase? = null
//
//        fun getInstance(context: Context): WidgetDatabase {
//            return instance ?: synchronized(this) {
//                instance ?: buildDatabase(context).also { instance = it }
//            }
//        }
//
//        private fun buildDatabase(context: Context) =
//            Room.databaseBuilder(context, AppDatabase::class.java, "userdb")
//                .build()
//    }
}