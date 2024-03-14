package com.hqnguyen.widgetapp.presentation.page.photo.edit

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CornerEdit(updateCorner: (size: Dp) -> Unit) {
    var sliderPosition by remember { mutableFloatStateOf(16f) }

    Text(
        text = "Corner",
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))
    Slider(
        value = sliderPosition,
        onValueChange = {
            sliderPosition = it
            updateCorner(it.dp)
        },
        steps = 48,
        valueRange = 0f..48f,
        modifier = Modifier
            .padding(vertical = 0.dp, horizontal = 16.dp)
    )
}