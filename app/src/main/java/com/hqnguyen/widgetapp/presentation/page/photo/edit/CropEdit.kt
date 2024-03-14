package com.hqnguyen.widgetapp.presentation.page.photo.edit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CropEdit(updateCropType: (cropType: Int) -> Unit) {
    val listCrop = listOf("Center Fit", "Center Crop", "Custom Crop")
    Text(
        text = "Crop",
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp, horizontal = 16.dp),
        userScrollEnabled = false,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(count = listCrop.size) {
            ElevatedCard(
                colors = CardColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.White,
                    disabledContentColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 6.dp
                ),
                shape = RoundedCornerShape(36.dp),
                modifier = Modifier.clickable {
                    updateCropType(it)
                }
            ) {
                Text(
                    text = listCrop[it],
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}