package com.hqnguyen.widgetapp.widget_glance

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.hqnguyen.widgetapp.widget_glance.worker.EventWorker

class EventWidgetReceiver: GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = EventWidgetApp()

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
        Log.d("EventWidgetReceiver", "onEnabled")
    }

    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
        Log.d("EventWidgetReceiver", "onDisabled")

        if (context != null) {
            EventWorker.cancel(context)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("EventWidgetReceiver", "onReceive")
        super.onReceive(context, intent)
        EventWorker.enqueue(context)
    }

    override fun onAppWidgetOptionsChanged(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
        newOptions: Bundle
    ) {
        Log.d("EventWidgetReceiver", "onAppWidgetOptionsChanged")
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
    }
}