package com.hqnguyen.widgetapp.widget_glance

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.glance.state.GlanceStateDefinition
import java.io.File

object EventStateDefinition : GlanceStateDefinition<EventInfo> {

    private const val DATA_STORE_FILENAME = "locationInfo"

//
//    private val Context.datastore by dataStore(DATA_STORE_FILENAME, WeatherInfoSerializer)
    override suspend fun getDataStore(context: Context, fileKey: String): DataStore<EventInfo> {
        return context.datastore
    }

    override fun getLocation(context: Context, fileKey: String): File {
        return context.dataStoreFile(DATA_STORE_FILENAME)
    }
}