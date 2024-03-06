package com.hqnguyen.widgetapp.presentation.page.photo

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FrameEdit(updateFrame: (frame: Int) -> Unit) {
    Text(
        text = "Border",
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))
}