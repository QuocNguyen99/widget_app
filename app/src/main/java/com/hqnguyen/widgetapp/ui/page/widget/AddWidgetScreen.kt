package com.hqnguyen.widgetapp.ui.page.widget

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.hqnguyen.widgetapp.data.TypeTemplate

@Composable
fun AddWidgetScreen(
    type: String?,
    onNavigation: (router: String) -> Unit,
    onBack: () -> Unit
) {
    LaunchedEffect(key1 = true, block = {
        Log.d("AddWidgetScreen", "type: $type")
    })
    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            Header(onBack)
        }

        type?.let {
            Log.d("AddWidgetScreen", "TypeTemplate.from(type): ${TypeTemplate.from(type)} ")
            when (TypeTemplate.from(type)) {
                else -> Unit
            }
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