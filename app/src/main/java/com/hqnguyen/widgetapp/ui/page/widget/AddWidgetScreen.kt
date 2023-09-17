package com.hqnguyen.widgetapp.ui.page.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun AddWidgetScreen(onNavigation: (router: String) -> Unit, onBack: () -> Unit) {
    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            Header(onBack)
        }
    }
}

@Composable
fun Header(onBack: () -> Unit) {
    Row {
        Icon(imageVector = Icons.Default.Close, "",
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(true)
                ) { onBack() })
        Text("Add Widget Screen")
    }
}