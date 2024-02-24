package com.hqnguyen.widgetapp.widget_glance

import android.content.Context
import android.graphics.BitmapFactory
import android.view.inputmethod.InputMethodSession.EventCallback
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalSize
import androidx.glance.appwidget.CircularProgressIndicator
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.ImageProvider
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.text.Text
import com.hqnguyen.widgetapp.R

class EventWidget : GlanceAppWidget() {
    companion object {
        private val thinMode = DpSize(120.dp, 120.dp)
        private val smallMode = DpSize(184.dp, 184.dp)
        private val mediumMode = DpSize(260.dp, 200.dp)
        private val largeMode = DpSize(260.dp, 280.dp)
    }

    override val stateDefinition = EventStateDefinition

    override val sizeMode: SizeMode = SizeMode.Responsive(
        setOf(thinMode, smallMode, mediumMode, largeMode)
    )


    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val state = currentState<EventInfo>()
            val size = LocalSize.current
            MaterialTheme {
                when (state) {
                    EventInfo.Loading -> {
                        Box(
                            modifier = appWidgetBackgroundModifier().then(GlanceModifier),
                            contentAlignment = Alignment.Center,
                            content = { CircularProgressIndicator() }
                        )
                    }

                    is EventInfo.Available -> {
                        Image(
                            provider = getImageProvider(state.eventData.path),
                            contentDescription = state.eventData.title
                        )
                    }

                    is EventInfo.Unavailable -> {
                        Column(
                            modifier = appWidgetBackgroundModifier().then(GlanceModifier),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            content = {
                                Text("Data not available")
                                Button("Refresh", actionRunCallback<EventAction>())
                            },
                        )
                    }
                }
            }
        }

    }

    private fun getImageProvider(path: String): ImageProvider {
        return if (path == "") {
            ImageProvider(R.mipmap.bg_study)
        } else {
            val bitmap = BitmapFactory.decodeFile(path)
            ImageProvider(bitmap)
        }
    }

}