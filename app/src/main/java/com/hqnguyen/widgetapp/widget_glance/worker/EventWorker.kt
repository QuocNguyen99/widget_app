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
import com.hqnguyen.widgetapp.widget_glance.EventInfo
import com.hqnguyen.widgetapp.widget_glance.EventStateDefinition
import com.hqnguyen.widgetapp.widget_glance.EventWidgetApp
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltWorker
class EventWorker @AssistedInject constructor(
    private var widgetDAO: WidgetDAO,
    @Assisted private val context: Context,
    @Assisted workerParameters: WorkerParameters,
) : CoroutineWorker(context, workerParameters) {

    companion object {

        private val uniqueWorkName = EventWorker::class.java.simpleName

        fun enqueue(context: Context, force: Boolean = false) {
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
            setWidgetState(glanceIds, EventInfo.Loading)
            Log.d("TAG", "doWork Start ")
            widgetDAO.getAllWidget().collectLatest {
                Log.d("TAG", "doWork: $it ")
                Result.success()
            }
            Result.success()
        } catch (ex: Exception) {
            Log.e("TAG", "doWork ex ${ex.message} ")
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