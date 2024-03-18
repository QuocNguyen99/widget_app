package com.hqnguyen.widgetapp.presentation.page.photo

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hqnguyen.widgetapp.presentation.page.photo.edit.CornerEdit
import com.hqnguyen.widgetapp.presentation.page.photo.edit.CropEdit
import com.hqnguyen.widgetapp.presentation.page.photo.edit.FrameEdit
import com.hqnguyen.widgetapp.presentation.page.photo.edit.ShapeEdit
import com.hqnguyen.widgetapp.presentation.page.widget.add.item.SizeEdit
import es.dmoral.toasty.Toasty

@SuppressLint("MutableCollectionMutableState")
@Composable
fun EditPhotoLayout(
    modifier: Modifier = Modifier,
    listPath: List<Uri>? = null,
    indexSize: Int = 0,
    indexShape: Int = 0,
    screenWidth: Dp,
    colorList: MutableList<List<Color>>,
    updateSize: (size: Int) -> Unit,
    updateCropType: (cropType: Int) -> Unit,
    updateBorderColor: (borderPosition: Int) -> Unit,
    updateCorner: (cornerSize: Dp) -> Unit,
    updateShape: (index: Int) -> Unit,
) {
    val context = LocalContext.current

    var isPositionFrameSelected by remember {
        mutableIntStateOf(0)
    }

    val messageChoose = "Please choose your photo."

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            SizeEdit(indexSize = indexSize) {
                if (listPath == null) {
                    Toasty.info(context, messageChoose, Toast.LENGTH_SHORT, true)
                        .show()
                    return@SizeEdit
                }
                updateSize(it)
//                updateShape(0)
            }
            Spacer(modifier = Modifier.height(16.dp))

            CropEdit {
                if (listPath == null) {
                    Toasty.info(context, messageChoose, Toast.LENGTH_SHORT, true)
                        .show()
                    return@CropEdit
                }
                updateCropType(it)
                Log.d("CropEdit", "CropEdit ")
            }
            Spacer(modifier = Modifier.height(16.dp))

            FrameEdit(colorList = colorList, isPositionSelected = isPositionFrameSelected) {
                if (listPath == null) {
                    Toasty.info(context, messageChoose, Toast.LENGTH_SHORT, true)
                        .show()
                    return@FrameEdit
                }
                isPositionFrameSelected = it
                updateBorderColor(it)
            }
            Spacer(modifier = Modifier.height(16.dp))

            CornerEdit {
                if (listPath == null) {
                    Toasty.info(context, messageChoose, Toast.LENGTH_SHORT, true)
                        .show()
                    return@CornerEdit
                }

                updateCorner(it)
            }
            Spacer(modifier = Modifier.height(16.dp))


//                ShapeEdit(indexShape = indexShape) {
//                    if (listPath == null) {
//                        Toasty.info(context, messageChoose, Toast.LENGTH_SHORT, true)
//                            .show()
//                        return@ShapeEdit
//                    }
//
//                    updateShape(it)
//                }
//                Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

fun randomColor(): MutableList<List<Color>> {
    val rainbowColorCodes =
        listOf(
            Color.Red,
            Color.Blue,
            Color.Yellow,
            Color.Green,
            Color.Gray,
            Color.Cyan,
            Color.DarkGray
        )

    val colorCombinations = mutableListOf<List<Color>>()

    for (color in rainbowColorCodes) {
        colorCombinations.add(listOf(color))
    }

    for (i in 0 until rainbowColorCodes.size - 1) {
        for (j in i + 1 until rainbowColorCodes.size) {
            colorCombinations.add(
                listOf(
                    rainbowColorCodes[i],
                    rainbowColorCodes[j]
                )
            )
        }
    }

    for (i in 0 until rainbowColorCodes.size - 2) {
        for (j in i + 1 until rainbowColorCodes.size - 1) {
            for (k in j + 1 until rainbowColorCodes.size) {
                colorCombinations.add(
                    listOf(
                        rainbowColorCodes[i],
                        rainbowColorCodes[j],
                        rainbowColorCodes[k]
                    )
                )
            }
        }
    }
    colorCombinations.add(0, listOf())
    return colorCombinations
}