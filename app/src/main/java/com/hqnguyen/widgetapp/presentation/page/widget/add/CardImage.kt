package com.hqnguyen.widgetapp.presentation.page.widget.add

import android.net.Uri
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.hqnguyen.widgetapp.R
import com.hqnguyen.widgetapp.presentation.page.widget.add.item.listCards
import java.io.File
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
    localPath: Uri? = null,
    @DrawableRes defaultImage: Int? = null,
    ) {
    Log.d("CardImage", "localPath: $localPath -- defaultImage: $defaultImage")

    val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.ROOT)

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier.width(screenWidth / listCards[indexSizeList].height)
    ) {
        ConstraintLayout {

            val (containerImageRef, defaultImageRef, localPathRef, titleRef, dateRef) = createRefs()

            Box(modifier = Modifier
                .width(screenWidth / listCards[indexSizeList].height)
                .height(screenWidth / listCards[indexSizeList].width)
                .constrainAs(containerImageRef) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }) {}

            if (localPath == null) {
                Image(painter = painterResource(id = defaultImage!!),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(screenWidth / listCards[indexSizeList].height)
                        .height(screenWidth / listCards[indexSizeList].width)
                        .constrainAs(defaultImageRef) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                        })
            } else {
                AsyncImage(
                    model = localPath,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(screenWidth / listCards[indexSizeList].height)
                        .height(screenWidth / listCards[indexSizeList].width)
                        .constrainAs(localPathRef) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                        })
            }

            Text(text = currentTitle,
                color = Color(currentColorText),
                maxLines = if (indexSizeList == listCards.size - 1) 1 else 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = currentSizeText.sp,
                modifier = Modifier
                    .padding(6.dp)
                    .constrainAs(titleRef) {
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
                    .constrainAs(dateRef) {
                        top.linkTo(titleRef.bottom)
                        start.linkTo(titleRef.start)
                    })
        }
    }
}
