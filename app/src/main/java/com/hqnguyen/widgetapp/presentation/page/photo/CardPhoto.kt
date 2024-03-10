package com.hqnguyen.widgetapp.presentation.page.photo

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.hqnguyen.widgetapp.R
import com.hqnguyen.widgetapp.presentation.page.widget.add.item.listCards

@Composable
fun CardPhoto(
    screenWidth: Dp,
    indexSizeList: Int = 0,
    path: Uri? = null,
    cropType: Int = 0,
    openMedia: () -> Unit,
    borderColor: Color? = Color.White
) {

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .padding(0.dp, vertical = 16.dp)
            .width(screenWidth / listCards[indexSizeList].height)
            .height(screenWidth / listCards[indexSizeList].width)
            .clickable {
                if (path == null) {
                    openMedia()
                }
            }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            if (path == null) {
                Image(
                    painter = painterResource(id = R.drawable.default_img),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    alpha = 0.4f,
                    modifier = Modifier
                        .width(screenWidth / 12)
                        .height(screenWidth / 12)
                )
            } else {
                SubcomposeAsyncImage(
                    model = path,
                    contentDescription = null,
                    contentScale = checkCropType(cropType),
                    loading = {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(4.dp)
                                .height(4.dp)
                                .padding(32.dp),
                            color = Color.Blue,
                            trackColor = Color.Gray,
                        )
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { openMedia() }
                        .clip(RoundedCornerShape(12.dp))
                )
            }
        }
    }
}

fun checkCropType(cropType: Int): ContentScale {
    return when (cropType) {
        0 -> ContentScale.Fit
        1 -> ContentScale.Crop
        2 -> ContentScale.None
        else -> ContentScale.Fit
    }
}