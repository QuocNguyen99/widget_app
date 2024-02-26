package com.hqnguyen.widgetapp.widget_glance

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import com.hqnguyen.widgetapp.widget_glance.worker.EventWorker

class EventAction: ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        EventWorker.enqueue(context)
    }
}