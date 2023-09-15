package com.hqnguyen.widgetapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.hqnguyen.widgetapp.ui.Header
import com.hqnguyen.widgetapp.ui.TabHeader
import com.hqnguyen.widgetapp.ui.theme.WidgetAppTheme

@Composable
fun WidgetApp() {
    Surface(color = Color(0xFFF5F5F5)) {
        Column {
            Header()
            TabHeader()
        }
    }
}


@Preview
@Composable
fun WidgetAppPreview() {
    WidgetAppTheme {
        WidgetApp()
    }
}