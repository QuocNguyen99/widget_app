package com.hqnguyen.widgetapp.presentation.page.event

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.hqnguyen.widgetapp.data.model.WidgetInfo

@Composable
fun EventScreen(viewModel: EventViewModel = hiltViewModel(), onNavigate: (route: String) -> Unit) {
    val eventUiState by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true, block = {
        Log.d("TAG", "EventScreen: ${eventUiState.listEvent.size}")
    })

    Surface(modifier = Modifier.fillMaxSize(), color = Color.LightGray) {
        LazyColumn {
            items(eventUiState.listEvent.size) {
                EventItem(eventUiState.listEvent[it])
            }
        }
    }
}

@Composable
fun EventItem(item: WidgetInfo, modifier: Modifier = Modifier) {

}