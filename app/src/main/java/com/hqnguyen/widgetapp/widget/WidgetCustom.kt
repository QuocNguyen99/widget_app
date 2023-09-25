package com.hqnguyen.widgetapp.widget

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.ActionParameters
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Box
import androidx.glance.layout.padding
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.Text
import com.hqnguyen.widgetapp.ui.theme.WidgetAppTheme

private val quotes = listOf(
    "Be yourself; everyone else is already taken. ― Oscar Wilde",
    "A room without books is like a body without a soul. ― Marcus Tullius Cicero",
    "You only live once, but if you do it right, once is enough. ― Mae West",
)
private val currentQuoteKey = stringPreferencesKey("currentQuote")

class WidgetCustom : GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val preferences = currentState<Preferences>()
            val currentQuote = preferences[currentQuoteKey] ?: quotes.random()

            MaterialTheme {
                Box(
                    modifier = GlanceModifier
                        .background(Color.White)
                        .padding(16.dp)
                        .clickable(actionRunCallback<RefreshQuoteAction>())
                ) {
                    Text(text = currentQuote)
                }
            }
        }
    }
}

class RefreshQuoteAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        updateAppWidgetState(context, PreferencesGlanceStateDefinition, glanceId) { preferences ->
            preferences.toMutablePreferences().apply {
                this[currentQuoteKey] = quotes.random()
            }
        }
        WidgetCustom().update(context, glanceId)
    }
}