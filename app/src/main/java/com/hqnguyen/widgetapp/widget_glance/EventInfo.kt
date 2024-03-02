package com.hqnguyen.widgetapp.widget_glance

import androidx.glance.GlanceId
import kotlinx.serialization.Serializable

@Serializable
sealed interface EventInfo {
    @Serializable
    data object Loading : EventInfo

    @Serializable
    data class Available(
        val eventData: EventData
    ) : EventInfo

    @Serializable
    data class Unavailable(val message: String) : EventInfo
}

@Serializable
data class EventData(
    val path: String,
    val title: String,
    val subTitle: String
)