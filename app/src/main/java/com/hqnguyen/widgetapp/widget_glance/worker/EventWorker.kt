package com.hqnguyen.widgetapp.widget_glance.worker

import android.content.Context
import android.util.Log
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.appwidget.updateAll
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.hqnguyen.widgetapp.data.dao.WidgetDAO
import com.hqnguyen.widgetapp.widget_glance.EventData
import com.hqnguyen.widgetapp.widget_glance.EventInfo
import com.hqnguyen.widgetapp.widget_glance.EventStateDefinition
import com.hqnguyen.widgetapp.widget_glance.EventWidgetApp
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@HiltWorker
class EventWorker @AssistedInject constructor(
    private var widgetDAO: WidgetDAO,
    @Assisted private val context: Context,
    @Assisted workerParameters: WorkerParameters,
) : CoroutineWorker(context, workerParameters) {

    companion object {

        private val uniqueWorkName = EventWorker::class.java.simpleName

        fun enqueue(context: Context) {
            val manager = WorkManager.getInstance(context)
            val requestBuilder = OneTimeWorkRequestBuilder<EventWorker>()
            val workPolicy = ExistingWorkPolicy.KEEP

            Log.d("TAG", "doWork enqueue")
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
        Log.d("TAG", "doWork Entry ")
        val manager = GlanceAppWidgetManager(context)
        val glanceIds = manager.getGlanceIds(EventWidgetApp::class.java)
        return try {
            val lastGlanceId = glanceIds.last()
            setWidgetState(lastGlanceId, EventInfo.Loading)
            Log.d("TAG", "doWork Start ")
            Log.d("TAG", "doWork glanceIds: $glanceIds ")
            val data = widgetDAO.getAllWidget().firstOrNull()
            val lastData = data?.last()
            Log.d("TAG", "doWork lastData: $lastData ")
            Log.d("TAG", "doWork lastGlanceId: $lastGlanceId ")
            if (lastData != null) {
                setWidgetState(
                    lastGlanceId,
                    EventInfo.Available(
                        eventData = EventData(
                            lastData.imagePath,
                            lastData.title,
                            lastData.title
                        ),
                    )
                )
            }
            Result.success()
        } catch (ex: Exception) {
            Log.e("TAG", "doWork ex ${ex.message} ")
            Result.failure()
        } finally {
            cancel(context)
        }
    }

    private fun setWidgetState(glanceId: GlanceId, newState: EventInfo) {
        MainScope().launch {
            updateAppWidgetState(
                context = context,
                definition = EventStateDefinition,
                glanceId = glanceId,
                updateState = { newState }
            )
            EventWidgetApp().update(context, glanceId)
        }
    }
}