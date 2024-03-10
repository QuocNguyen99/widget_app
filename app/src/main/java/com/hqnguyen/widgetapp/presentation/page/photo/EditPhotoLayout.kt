package com.hqnguyen.widgetapp.presentation.page.photo

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hqnguyen.widgetapp.presentation.page.widget.add.item.SizeEdit
import es.dmoral.toasty.Toasty

@SuppressLint("MutableCollectionMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditPhotoLayout(
    modifier: Modifier = Modifier,
    path: Uri? = null,
    screenWidth: Dp,
    updateSize: (size: Int) -> Unit,
    updateCropType: (cropType: Int) -> Unit,
) {

    val context = LocalContext.current

    var colorList by remember {
        mutableStateOf(mutableListOf<List<Color>>())
    }

    var isPositionFrameSelected by remember {
        mutableIntStateOf(0)
    }

    val messageChoose = "Please choose your photo."

    LaunchedEffect(key1 = Unit, block = {
        colorList = randomColor()
    })

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
            SizeEdit {
                if (path == null) {
                    Toasty.info(context, messageChoose, Toast.LENGTH_SHORT, true)
                        .show()
                    return@SizeEdit
                }
                updateSize(it)
            }
            Spacer(modifier = Modifier.height(16.dp))

            CropEdit {
                if (path == null) {
                    Toasty.info(context, messageChoose, Toast.LENGTH_SHORT, true)
                        .show()
                    return@CropEdit
                }
                updateCropType(it)
                Log.d("CropEdit", "CropEdit ")
            }
            Spacer(modifier = Modifier.height(16.dp))

            FrameEdit(colorList = colorList, isPositionSelected = isPositionFrameSelected) {
                if (path == null) {
                    Toasty.info(context, messageChoose, Toast.LENGTH_SHORT, true)
                        .show()
                    return@FrameEdit
                }
                isPositionFrameSelected = it
            }
            Spacer(modifier = Modifier.height(16.dp))

            CornerEdit {
                if (path == null) {
                    Toasty.info(context, messageChoose, Toast.LENGTH_SHORT, true)
                        .show()
                    return@CornerEdit
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

fun checkPathIsExit(context: Context, path: Uri?) {
    if (path == null) {
        Toasty.info(context, "Please choose your photo.", Toast.LENGTH_SHORT, true)
            .show()
        return
    }
}

@RequiresApi(Build.VERSION_CODES.O)
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