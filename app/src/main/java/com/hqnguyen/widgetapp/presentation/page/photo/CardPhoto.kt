package com.hqnguyen.widgetapp.presentation.page.photo

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.rectangle
import androidx.graphics.shapes.star
import androidx.graphics.shapes.toPath
import coil.compose.SubcomposeAsyncImage
import com.hqnguyen.widgetapp.R
import com.hqnguyen.widgetapp.presentation.page.widget.add.item.listCards
import kotlin.math.max

@Composable
fun CardPhoto(
    screenWidth: Dp,
    indexSizeList: Int = 0,
    indexShape: Int = 0,
    path: Uri? = null,
    cropType: Int = 0,
    cornerSize: Dp = 16.dp,
    openMedia: () -> Unit,
    borderColor: List<Color> = listOf(Color.White)
) {

    Card(
        shape = if (indexSizeList == 1) RoundedCornerShape(16.dp) else CardDefaults.shape,
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
            .graphicsLayer {
                if (indexSizeList != 1) {
                    this.shape = RoundedPolygonShape(
                        polygon = shapeType(indexShape, size)
                    )
                    this.clip = true
                    if (indexShape == 1) {
                        this.rotationZ = 90f
                    }
                    if (indexShape == 2) {
                        this.rotationZ = -18f
                    }
                }
            },
        border = if (borderColor.isEmpty()) null else {
            if (borderColor.size == 1) BorderStroke(
                2.dp,
                borderColor.first()
            ) else BorderStroke(2.dp, Brush.linearGradient(colors = borderColor))
        },
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
                        .graphicsLayer {
                            if (indexSizeList != 1) {
                                this.shape = RoundedPolygonShape(
                                    polygon = shapeType(indexShape, size)
                                )
                                this.clip = true
                            }
                        }
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

fun RoundedPolygon.getBounds() = calculateBounds().let { Rect(it[0], it[1], it[2], it[3]) }
class RoundedPolygonShape(
    private val polygon: RoundedPolygon,
    private var matrix: Matrix = Matrix()
) : Shape {
    private var path = Path()
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        path.rewind()
        path = polygon.toPath().asComposePath()
        matrix.reset()
        val bounds = polygon.getBounds()
        val maxDimension = max(bounds.width, bounds.height)
        matrix.scale(size.width / maxDimension, size.height / maxDimension)
        matrix.translate(-bounds.left, -bounds.top)

        path.transform(matrix)
        return Outline.Generic(path)
    }
}

fun shapeType(index: Int, size: Size): RoundedPolygon {
    return when (index) {
        0 -> RoundedPolygon
            .rectangle(
                rounding = CornerRounding(0.4f),
                centerX = size.width / 2,
                centerY = size.height / 2,
            )
            .normalized()

        1 -> RoundedPolygon(
            numVertices = 3,
            rounding = CornerRounding(0.2f, 0.5f),
            centerX = size.width / 2,
            centerY = size.height / 2,
        )
            .normalized()

        2 -> RoundedPolygon(
            6,
            rounding = CornerRounding(0.0f),
            centerX = size.width / 2,
            centerY = size.height / 2,
        )

        3 -> RoundedPolygon(
            8,
            rounding = CornerRounding(0.0f),
            centerX = size.width / 2,
            centerY = size.height / 2,
        ).normalized()

        4 -> RoundedPolygon
            .star(
                8,
                rounding = CornerRounding(0.0f),
                centerX = size.width / 2,
                centerY = size.height / 2,
            )
            .normalized()

        5 -> RoundedPolygon
            .star(
                8,
                centerX = size.width / 2,
                centerY = size.height / 2,
                rounding = CornerRounding(.4f, .5f)
            )
            .normalized()

        else -> RoundedPolygon
            .rectangle(
                rounding = CornerRounding(0.4f),
                centerX = size.width / 2,
                centerY = size.height / 2,
            )
            .normalized()
    }
}