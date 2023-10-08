package com.hqnguyen.widgetapp.presentation.page.widget.add.item

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hqnguyen.widgetapp.R

@Composable
fun BackgroundEdit(
    onClickPhoto: () -> Unit, onClickDefaultPhoto: () -> Unit
) {
    Text(
        text = "Background",
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
    )
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ItemEditBackground(type = 0, "Background", R.mipmap.bg_wedding, onClickPhoto = onClickPhoto)
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .width(1.dp)
                .height(40.dp)
                .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.width(22.dp))
        ItemEditBackground(type = 1, "Effect", R.mipmap.bg_birthday)
        Spacer(modifier = Modifier.width(22.dp))
        ItemEditBackground(
            type = 2,
            "Default",
            R.mipmap.bg_study,
            onClickDefaultPhoto = onClickDefaultPhoto
        )
    }
}

@Composable
fun ItemEditBackground(
    type: Int,
    title: String,
    @DrawableRes idBackground: Int,
    onClickPhoto: () -> Unit = {},
    onClickDefaultPhoto: () -> Unit = {}
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = idBackground),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable {
                    Log.d("ItemEditBackground", "ItemEditBackground: $type")
                    when (type) {
                        0 -> onClickPhoto()
                        1 -> {}
                        2 -> onClickDefaultPhoto()
                    }
                }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = TextStyle(fontWeight = FontWeight.Light, fontSize = 12.sp, color = Color.Gray)
        )
    }
}


