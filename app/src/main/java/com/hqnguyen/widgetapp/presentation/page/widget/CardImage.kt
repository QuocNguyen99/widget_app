package com.hqnguyen.widgetapp.presentation.page.widget

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.hqnguyen.widgetapp.R
import com.hqnguyen.widgetapp.presentation.page.widget.item.listCards
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CardImage(
    screenWidth: Dp,
    indexSizeList: Int = 0,
    currentTitle: String = "Hello",
    currentDate: Long = System.currentTimeMillis(),
    currentSizeText: Float = 9F,
    currentColorText: Int = Color.White.toArgb(),
) {
    Log.d("CardImage", "title: $currentDate -- date: $currentDate indexSize: $indexSizeList")

    val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.ROOT)

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier.width(screenWidth / listCards[indexSizeList].height)
    ) {
        ConstraintLayout {

            val (image, title, date) = createRefs()

            Image(painter = painterResource(id = R.mipmap.bg_wedding),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(screenWidth / listCards[indexSizeList].height)
                    .height(screenWidth / listCards[indexSizeList].width)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    })

            Text(text = currentTitle,
                color = Color(currentColorText),
                maxLines = if (indexSizeList == listCards.size - 1) 1 else 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = currentSizeText.sp,
                modifier = Modifier
                    .padding(6.dp)
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    })

            Text(text = formatter.format(currentDate),
                color = Color(currentColorText),
                maxLines = if (indexSizeList == listCards.size - 1) 1 else 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = currentSizeText.sp,
                modifier = Modifier
                    .padding(6.dp)
                    .constrainAs(date) {
                        top.linkTo(title.bottom)
                        start.linkTo(title.start)
                    })
        }
    }
}
