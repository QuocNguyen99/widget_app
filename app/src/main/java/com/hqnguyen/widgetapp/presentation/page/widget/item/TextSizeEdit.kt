package com.hqnguyen.widgetapp.presentation.page.widget.item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextSizeEdit(defaultTextSize: Float, updateCurrentTexSize: (size: Float) -> Unit) {
    var sliderPosition by remember { mutableFloatStateOf(defaultTextSize) }

    Text(
        text = "Font size",
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
    )
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Slider(
            value = sliderPosition,
            valueRange = 9f..14f,
            steps = 5,
            onValueChange = {
                sliderPosition = it
                updateCurrentTexSize(it)
            },
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFF6ac5fe),
                activeTrackColor = Color(0xFF6ac5fe),
                inactiveTrackColor = Color.LightGray,
            )
        )
    }
}
