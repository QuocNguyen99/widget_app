package com.hqnguyen.widgetapp.presentation.page.widget.add.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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

data class CardDefault(val width: Int, val height: Int)

val listCards = listOf(
    CardDefault(2, 2),
    CardDefault(4, 2),
    CardDefault(4, 4),
)

@Composable
fun SizeEdit(onClickCardSize: (index: Int) -> Unit) {
    Text(
        text = "Size",
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp),

        )
    Spacer(
        modifier = Modifier
            .height(8.dp)
            .padding(vertical = 0.dp, horizontal = 16.dp)
    )
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 0.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ItemEditSize(
            2,
            "2x2",
            listCards[listCards.size - 3].width,
            listCards[listCards.size - 3].height
        ) { onClickCardSize(it) }
        Spacer(modifier = Modifier.width(22.dp))
        ItemEditSize(
            1,
            "2x4",
            listCards[listCards.size - 2].width,
            listCards[listCards.size - 2].height
        ) { onClickCardSize(it) }
        Spacer(modifier = Modifier.width(22.dp))
        ItemEditSize(
            0,
            "4x4",
            listCards[listCards.size - 1].width,
            listCards[listCards.size - 1].height
        ) { onClickCardSize(it) }
    }
}
