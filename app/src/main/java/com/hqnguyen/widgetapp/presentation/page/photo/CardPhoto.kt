package com.hqnguyen.widgetapp.presentation.page.photo

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardPhoto(
    screenWidth: Dp,
    indexSizeList: Int = 0,
    indexShape: Int = 0,
    listPath: List<Uri>? = null,
    cropType: Int = 0,
    cornerSize: Dp = 16.dp,
    openMedia: () -> Unit,
    borderColor: List<Color> = listOf(Color.White),
    updateCurrentImageDisplay: (page: Int) -> Unit = {}
) {

    val pagerState = rememberPagerState(pageCount = {
        listPath?.size ?: 0
    })

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            Log.d("CardPhoto", "Page changed to $page")
            updateCurrentImageDisplay(page)
        }
    }

    if (listPath.isNullOrEmpty()) {
        Card(
            onClick = { openMedia() },
            shape = RoundedCornerShape(cornerSize),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .width(screenWidth / listCards[indexSizeList].height)
                    .height(screenWidth / listCards[indexSizeList].width),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.default_img),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    alpha = 0.4f,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .width(screenWidth / 12)
                        .height(screenWidth / 12)
                )
            }
        }
    } else {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .width(screenWidth / listCards[indexSizeList].height + 36.dp)
                .height(screenWidth / listCards[indexSizeList].width + 36.dp)
        ) { page ->
            Card(
                modifier = Modifier
                    .padding(16.dp),
                shape = RoundedCornerShape(cornerSize),
                elevation = CardDefaults.cardElevation(8.dp),
                border = if (borderColor.isEmpty()) null else {
                    if (borderColor.size == 1) BorderStroke(
                        2.dp,
                        borderColor.first()
                    ) else BorderStroke(2.dp, Brush.linearGradient(colors = borderColor))
                },
            ) {
                SubcomposeAsyncImage(
                    model = listPath[page],
                    contentDescription = null,
                    contentScale = checkCropType(cropType),
                    loading = {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(4.dp)
                                .height(4.dp)
                                .padding(32.dp),
                            color = Color.DarkGray,
                            trackColor = Color.Gray,
                        )
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { openMedia() }
                        .clip(RoundedCornerShape(cornerSize))
                )
            }
        }

        if (listPath.size > 1) {
            DotsIndicator(
                totalDots = listPath.size,
                selectedIndex = pagerState.currentPage,
                selectedColor = Color.Black,
                unSelectedColor = Color.Gray
            )

            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,
) {
    if (totalDots != 0)
        LazyRow(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()

        ) {

            items(totalDots) { index ->
                if (index == selectedIndex) {
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(selectedColor)
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(unSelectedColor)
                    )
                }

                if (index != totalDots - 1) {
                    Spacer(modifier = Modifier.padding(horizontal = 2.dp))
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