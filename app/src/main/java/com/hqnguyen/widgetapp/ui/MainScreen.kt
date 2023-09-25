package com.hqnguyen.widgetapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.hqnguyen.widgetapp.presentation.custom.HeaderMain
import com.hqnguyen.widgetapp.presentation.custom.TabHeader

@Composable
fun MainScreen(onNavigate: (route: String) -> Unit) {
    Column {
        HeaderMain(onNavigate)
        TabHeader(onNavigate)
    }
}