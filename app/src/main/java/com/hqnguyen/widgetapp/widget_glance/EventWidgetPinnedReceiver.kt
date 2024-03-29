package com.hqnguyen.widgetapp.widget_glance

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class EventWidgetPinnedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("TAG", "onReceive: intent ${intent?.data} -- ${intent?.action}")
        Toast.makeText(
            context,
            "Widget pinned. Go to home screen.",
            Toast.LENGTH_SHORT
        ).show()
    }
}