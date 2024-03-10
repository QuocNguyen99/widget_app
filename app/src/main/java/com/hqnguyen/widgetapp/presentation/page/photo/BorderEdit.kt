package com.hqnguyen.widgetapp.presentation.page.photo

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hqnguyen.widgetapp.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FrameEdit(
    isPositionSelected: Int = 0,
    colorList: List<List<Color>>,
    updateFrame: (frame: Int) -> Unit
) {
    Log.d("FrameEdit", "FrameEdit item : ${colorList.size} ")
    Text(
        text = "Border",
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))

    LazyRow(modifier = Modifier.padding(16.dp)) {
        items(colorList.size) {
            Surface(
                color = Color.White,
                border = if (isPositionSelected == it) BorderStroke(2.dp, Color.DarkGray) else null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(64.dp)
                    .clickable {
                        updateFrame(it)
                    },
                shape = RoundedCornerShape(8.dp)
            ) {
                if (colorList[it].isNotEmpty()) {
                    Surface(
                        color = Color.White,
                        border = if (colorList[it].size == 1) BorderStroke(
                            2.dp,
                            colorList[it].first()
                        ) else
                            BorderStroke(2.dp, Brush.linearGradient(colors = colorList[it])),
                        modifier = Modifier
                            .padding(vertical = 4.dp, horizontal = 4.dp)
                            .size(64.dp)
                            .clickable {
                                updateFrame(it)
                            },
                        shape = RoundedCornerShape(8.dp)
                    ) { }
                } else {
                    Surface(
                        color = Color.White,
                        border = BorderStroke(2.dp, Color.LightGray),
                        modifier = Modifier
                            .padding(vertical = 4.dp, horizontal = 4.dp)
                            .size(64.dp)
                            .clickable {
                                updateFrame(it)
                            },
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_block),
                            contentDescription = "",
                            Modifier
                                .size(16.dp)
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}