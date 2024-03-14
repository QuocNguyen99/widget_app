package com.hqnguyen.widgetapp.presentation.page.photo.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.rectangle
import androidx.graphics.shapes.star
import com.hqnguyen.widgetapp.presentation.page.photo.RoundedPolygonShape

@Composable
fun ShapeEdit(indexShape: Int, updateShape: (index: Int) -> Unit) {

    val rounding = remember {
        val roundingNormal = 1f
        val roundingNone = 0f
        listOf(
            CornerRounding(roundingNormal),
            CornerRounding(roundingNone),
            CornerRounding(roundingNormal),
            CornerRounding(roundingNormal),
            CornerRounding(roundingNone),
            CornerRounding(roundingNormal),
        )
    }

//    val listShape = listOf(
//        RoundedPolygon
//            .rectangle(
//                rounding = CornerRounding(0.4f),
//            ),
//        RoundedPolygon(
//            5,
//            rounding = CornerRounding(0.0f),
//        ),
//        RoundedPolygon(
//            8,
//            rounding = CornerRounding(0.0f)
//        ),
//        RoundedPolygon
//            .star(
//                8,
//                rounding = CornerRounding(0.0f)
//            ),
//        RoundedPolygon
//            .star(
//                8,
//                rounding = CornerRounding(.4f, .5f)
//            )
//    )

    Text(
        text = "Shape",
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))

    // TODO will fix
//    LazyRow() {
//        items(listShape.size) {
//            Box(
//                modifier = Modifier
//                    .padding(0.dp, 0.dp, 8.dp, 0.dp)
//                    .size(60.dp)
//                    .graphicsLayer {
//                        this.shape = RoundedPolygonShape(
//                            polygon = listShape[it]
//                        )
//                        this.clip = true
//                    }
//                    .background(color = Color.LightGray)
//
//            )
//        }
//    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp, horizontal = 16.dp)
            .horizontalScroll(rememberScrollState())
    ) {

        Box(
            modifier = Modifier
                .padding(0.dp, 0.dp, 8.dp, 0.dp)
                .size(55.dp)
                .graphicsLayer {
                    this.scaleX = 1f
                    this.scaleY = 1f
                    this.shape = RoundedPolygonShape(
                        polygon = RoundedPolygon
                            .rectangle(
                                rounding = CornerRounding(0.4f),
                                centerX = size.width / 2,
                                centerY = size.height / 2,
                            )
                            .normalized()
                    )
                    this.clip = true
                }
                .background(color = if (indexShape == 0) Color(0xFF6ac5fe) else Color.LightGray)
                .clickable { updateShape(0) }
        )

        Box(
            modifier = Modifier
                .padding(8.dp, 0.dp)
                .size(60.dp)
                .graphicsLayer {
                    this.shape = RoundedPolygonShape(
                        polygon = RoundedPolygon(
                            numVertices = 3,
                            rounding = CornerRounding(0.2f, 0.5f),
                            centerX = size.width / 2,
                            centerY = size.height / 2,
                        )
                            .normalized()
                    )
                    this.clip = true
                    this.rotationZ = 90f
                }
                .background(color = if (indexShape == 1) Color(0xFF6ac5fe) else Color.LightGray)
                .clickable { updateShape(1) }

        )

        Box(
            modifier = Modifier
                .padding(8.dp, 0.dp)
                .size(60.dp)
                .graphicsLayer {
                    this.shape = RoundedPolygonShape(
                        polygon = RoundedPolygon(
                            6,
                            rounding = CornerRounding(0.0f),
                            centerX = size.width / 2,
                            centerY = size.height / 2,
                        ).normalized()
                    )
                    this.clip = true
                }
                .background(color = if (indexShape == 2) Color(0xFF6ac5fe) else Color.LightGray)
                .clickable { updateShape(2) }

        )

        Box(
            modifier = Modifier
                .padding(8.dp, 0.dp)
                .size(60.dp)
                .graphicsLayer {
                    this.shape = RoundedPolygonShape(
                        polygon = RoundedPolygon(
                            8,
                            rounding = CornerRounding(0.0f),
                            centerX = size.width / 2,
                            centerY = size.height / 2,
                        ).normalized()
                    )
                    this.clip = true
                }
                .background(color = if (indexShape == 3) Color(0xFF6ac5fe) else Color.LightGray)
                .clickable { updateShape(3) }

        )

        Box(
            modifier = Modifier
                .padding(8.dp, 0.dp)
                .size(60.dp)
                .graphicsLayer {
                    this.shape = RoundedPolygonShape(
                        polygon = RoundedPolygon
                            .star(
                                8,
                                rounding = CornerRounding(0.0f),
                                centerX = size.width / 2,
                                centerY = size.height / 2,
                            )
                            .normalized()
                    )
                    this.clip = true
                }
                .background(color = if (indexShape == 4) Color(0xFF6ac5fe) else Color.LightGray)
                .clickable { updateShape(4) }

        )

        Box(
            modifier = Modifier
                .padding(8.dp, 0.dp)
                .size(60.dp)
                .graphicsLayer {
                    this.shape = RoundedPolygonShape(
                        polygon = RoundedPolygon
                            .star(
                                8,
                                centerX = size.width / 2,
                                centerY = size.height / 2,
                                rounding = CornerRounding(.4f, .5f)
                            )
                            .normalized()
                    )
                    this.clip = true
                }
                .background(color = if (indexShape == 5) Color(0xFF6ac5fe) else Color.LightGray)
                .clickable { updateShape(5) }
        )
    }
}