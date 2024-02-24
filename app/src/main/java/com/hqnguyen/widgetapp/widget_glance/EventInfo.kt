package com.hqnguyen.widgetapp.widget_glance

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

//@Serializable
//sealed interface LocationInfo {
//    @Serializable
//    object Loading : LocationInfo
//
//    @Serializable
//    data class Available(
//        val placeName: String,
//        val currentData: LocationData,
//    ) : LocationInfo
//
//    @Serializable
//    data class Unavailable(val message: String) : LocationInfo
//}
//
@Serializable
data class EventData(
    val path: String,
    val title: String,
    val subTitle: String
)