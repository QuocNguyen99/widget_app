package com.hqnguyen.widgetapp.presentation.page.widget.add.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemEditSize(
    indexSize: Int,
    index: Int = 0,
    title: String,
    width: Int,
    height: Int,
    onClick: (index: Int) -> Unit
) {
    val defaultSize = 15.dp
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            color = if (indexSize == index) Color(0xFF6ac5fe) else Color.LightGray,
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .width(defaultSize * width)
                .height(defaultSize * height)
                .clip(RoundedCornerShape(4.dp))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true)
                ) { onClick(index) }
        ) {

        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = TextStyle(fontWeight = FontWeight.Light, fontSize = 12.sp, color = Color.Gray)
        )
    }
}