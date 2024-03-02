package com.hqnguyen.widgetapp.widget_glance

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class EventWidgetPinnedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("TAG", "onReceiveeee: intent ${intent?.data} -- ${intent?.action}")
        Toast.makeText(
            context,
            "Widget pinned. Go to homescreen.",
            Toast.LENGTH_SHORT
        ).show()
    }
}