package com.hqnguyen.widgetapp.widget_glance

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import androidx.glance.state.GlanceStateDefinition
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.File
import java.io.InputStream
import java.io.OutputStream

object EventStateDefinition : GlanceStateDefinition<EventInfo> {

    private const val DATA_STORE_FILENAME = "eventInfo"

    private val Context.datastore by dataStore(DATA_STORE_FILENAME, EventInfoSerializer)

    override suspend fun getDataStore(context: Context, fileKey: String): DataStore<EventInfo> {
        return context.datastore
    }

    override fun getLocation(context: Context, fileKey: String): File {
        return context.dataStoreFile(DATA_STORE_FILENAME)
    }

    object EventInfoSerializer : Serializer<EventInfo> {
        override val defaultValue = EventInfo.Unavailable("no event found")

        override suspend fun readFrom(input: InputStream): EventInfo = try {
            Json.decodeFromString(
                EventInfo.serializer(),
                input.readBytes().decodeToString()
            )
        } catch (exception: SerializationException) {
            throw CorruptionException("Could not read event data: ${exception.message}")
        }

        override suspend fun writeTo(t: EventInfo, output: OutputStream) {
            output.use {
                it.write(
                    Json.encodeToString(EventInfo.serializer(), t).encodeToByteArray()
                )
            }
        }
    }
}