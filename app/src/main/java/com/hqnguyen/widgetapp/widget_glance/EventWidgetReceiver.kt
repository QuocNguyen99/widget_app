package com.hqnguyen.widgetapp.widget_glance

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.hqnguyen.widgetapp.widget_glance.worker.EventWorker

class EventWidgetReceiver: GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = EventWidgetApp()

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
        if (context != null) {
            EventWorker.enqueue(context)
        }
    }

    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
        if (context != null) {
            EventWorker.cancel(context)
        }
    }
}