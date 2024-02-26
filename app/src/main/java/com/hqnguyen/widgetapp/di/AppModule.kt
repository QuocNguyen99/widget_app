package com.hqnguyen.widgetapp.di

import android.content.Context
import androidx.room.Room
import com.hqnguyen.widgetapp.data.dao.WidgetDAO
import com.hqnguyen.widgetapp.data.database.WidgetDatabase
import com.hqnguyen.widgetapp.data.repository.WidgetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): WidgetDatabase =
        Room.databaseBuilder(
            context = context,
            WidgetDatabase::class.java, "database-widget"
        ).build()

    @Provides
    @Singleton
    fun providesWidgetDao(widgetDatabase: WidgetDatabase): WidgetDAO =
        widgetDatabase.widgetDAO()

    @Provides
    @Singleton
    fun providesWidgetRepository(widgetDAO: WidgetDAO): WidgetRepository =
        WidgetRepository(widgetDAO)
}