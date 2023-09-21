package com.hqnguyen.widgetapp.ui.page.widget.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hqnguyen.widgetapp.R
import com.hqnguyen.widgetapp.ui.page.widget.ItemEditBackground

@Composable
fun BackgroundEdit() {
    Text(
        text = "Background",
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
    )
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ItemEditBackground("Background", R.mipmap.bg_wedding)
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .width(1.dp)
                .height(40.dp)
                .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.width(22.dp))
        ItemEditBackground("Effect", R.mipmap.bg_birthday)
        Spacer(modifier = Modifier.width(22.dp))
        ItemEditBackground("Default", R.mipmap.bg_study)
    }
}


