package com.hqnguyen.widgetapp.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hqnguyen.widgetapp.HeaderItems
import com.hqnguyen.widgetapp.HeaderSubItems

@Composable
fun ButtonSubHeader(
    modifier: Modifier = Modifier,
    title: HeaderSubItems = HeaderSubItems.PLAN,
    colorContainer: Color = Color.Green,
    color: Color = Color.Red,
    onClickSubHeaderItem: (item: HeaderSubItems) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(4.dp))
            .clickable { onClickSubHeaderItem(title) }
            .background(colorContainer)
            .padding(6.dp)
    ) {
        Row {

            Text(
                text = title.name,
                style = TextStyle(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Surface(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .height(20.dp)
                    .width(4.dp),
                color = color
            ) {}

        }
    }
    Spacer(modifier = Modifier.width(8.dp))
}

@Preview
@Composable
fun ButtonSubHeaderPreview() {
    ButtonSubHeader(title = HeaderSubItems.PLAN)
}