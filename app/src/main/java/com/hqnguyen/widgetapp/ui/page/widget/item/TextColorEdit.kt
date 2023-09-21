package com.hqnguyen.widgetapp.ui.page.widget.item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextColorEdit() {
    val listColor =
        listOf(
            Color.Red,
            Color.Yellow,
            Color.Blue,
            Color.Black,
            Color.Cyan,
            Color.Green,
            Color.Red,
            Color.Yellow,
            Color.Blue,
            Color.Black,
            Color.Cyan,
            Color.Green,
            Color.Red,
            Color.Yellow,
            Color.Blue,
            Color.Black,
        )

    Text(
        text = "Color text",
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
    )
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LazyRow {
            items(count = listColor.size) { index ->
                Surface(
                    modifier = Modifier
                        .size(20.dp),
                    shape = CircleShape,
                    color = listColor[index]
                ) { }
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}
