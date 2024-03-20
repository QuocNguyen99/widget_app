package com.hqnguyen.widgetapp.presentation.page.photo.edit

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.WifiProtectedSetup
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimeIntervalEdit(selectedIndex: Int = 0, updateTimeInterval: () -> Unit) {
    val listCrop = listOf("None", "Center Crop", "Custom Crop", "Custom Crop", "Custom Crop")
    Text(
        text = "Time Interval",
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))

    Row(modifier = Modifier
        .padding(16.dp, 0.dp)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(bounded = true)
        ) {

        }, verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 8.dp)
        ) {
            Image(
                imageVector = Icons.Default.AccessTime,
                contentDescription = ""
            )
        }
        Column(modifier = Modifier.weight(weight = 1f)) {
            Text(text = "Select Interval", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(text = "30 second", fontSize = 12.sp)
        }
        Column() {
            Image(imageVector = Icons.Default.WifiProtectedSetup, contentDescription = "")
        }
    }
}