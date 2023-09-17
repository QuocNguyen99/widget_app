package com.hqnguyen.widgetapp.ui.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hqnguyen.widgetapp.ui.page.event.EventScreen
import com.hqnguyen.widgetapp.ui.page.history.HistoryScreen
import com.hqnguyen.widgetapp.ui.page.home.HomeScreen

@Composable
fun HeaderMain(onNavigate: (route: String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        Text(text = "Widget App")
    }
}

@Composable
fun TabHeader(onNavigate: (route: String) -> Unit) {
    var tabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("Home", "Event", "History")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = Color.White,
            contentColor = Color(0xFF6ac5fe),
            divider = { Divider(color = Color.LightGray) },
            indicator = { tabPositions ->
                if (tabIndex < tabPositions.size) {
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                        color = Color(0xFF6ac5fe),
                        height = 2.dp
                    )
                }
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            title,
                            style = TextStyle(fontWeight = if (tabIndex == index) FontWeight.Bold else FontWeight.Light)
                        )
                    },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                )
            }
        }
        when (tabIndex) {
            0 -> HomeScreen(onNavigate =  onNavigate)
            1 -> EventScreen(onNavigate)
            2 -> HistoryScreen(onNavigate)
        }
    }
}