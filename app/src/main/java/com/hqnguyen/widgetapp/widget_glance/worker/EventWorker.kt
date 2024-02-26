package com.hqnguyen.widgetapp.widget_glance.worker

import android.content.Context
import android.util.Log
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.appwidget.updateAll
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.hqnguyen.widgetapp.data.dao.WidgetDAO
import com.hqnguyen.widgetapp.widget_glance.EventInfo
import com.hqnguyen.widgetapp.widget_glance.EventStateDefinition
import com.hqnguyen.widgetapp.widget_glance.EventWidgetApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class EventWorker @Inject constructor(
    private val context: Context,
    workerParameters: WorkerParameters,
    private val widgetDAO: WidgetDAO
) : CoroutineWorker(context, workerParameters) {
    companion object {

        private val uniqueWorkName = EventWorker::class.java.simpleName

        fun enqueue(context: Context, force: Boolean = false) {
            val manager = WorkManager.getInstance(context)
            val requestBuilder = OneTimeWorkRequestBuilder<EventWorker>()
                .setInitialDelay(1, TimeUnit.MINUTES)
            var workPolicy = ExistingWorkPolicy.KEEP

            // Replace any enqueued work and expedite the request
            if (force) {
                workPolicy = ExistingWorkPolicy.REPLACE
            }

            manager.enqueueUniqueWork(
                uniqueWorkName,
                workPolicy,
                requestBuilder.build()
            )
        }

        fun cancel(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(uniqueWorkName)
        }
    }

    override suspend fun doWork(): Result {
        Log.d("TAG", "doWork Start ")
        val manager = GlanceAppWidgetManager(context)
        val glanceIds = manager.getGlanceIds(EventWidgetApp::class.java)
        return try {

            setWidgetState(glanceIds, EventInfo.Loading)
            CoroutineScope(Dispatchers.IO)
                .launch {
                    Log.d("TAG", "doWork Start ")

                    val result = widgetDAO.getAllWidget()
                    Log.d("TAG", "doWork: $result ")
                }
            enqueue(context)
            Result.success()
        } catch (ex: Exception) {
            Result.failure()
        }
    }

    private fun setWidgetState(glanceIds: List<GlanceId>, newState: EventInfo) {
        MainScope().launch {
            glanceIds.forEach { glanceId ->
                updateAppWidgetState(
                    context = context,
                    definition = EventStateDefinition,
                    glanceId = glanceId,
                    updateState = { newState }
                )
            }
            EventWidgetApp().updateAll(context)
        }
    }
}