package com.hqnguyen.widgetapp.presentation.page.photo

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.hqnguyen.widgetapp.presentation.custom.AppBar
import com.hqnguyen.widgetapp.utils.openPhotoPicker

@Composable
fun EditPhotoScreen(
    currentPage: String? = "",
    navController: NavHostController? = null,
    viewModel: EditPhotoViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val state by viewModel.state.collectAsState()

    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            Log.d("EditPhotoScreen", "Selected URI: $uri")
            uri?.let {
                viewModel.handleEvents(EditPhotoEvent.UpdatePhoto(it))
                return@rememberLauncherForActivityResult
            }
            Log.e("EditPhotoScreen", "No media selected")
        }

    val imageCropLauncher =
        rememberLauncherForActivityResult(contract = CropImageContract()) { result ->
            if (result.isSuccessful) {
                result.uriContent?.let {
                    val bitmap = if (Build.VERSION.SDK_INT < 28) {
                        MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                    } else {
                        val source = ImageDecoder.createSource(context.contentResolver, it)
                        ImageDecoder.decodeBitmap(source)
                    }

                    Log.d("TAG", "EditPhotoScreen: bitmap ${bitmap.width}")
                    viewModel.handleEvents(EditPhotoEvent.UpdatePhoto(it))
                    viewModel.handleEvents(EditPhotoEvent.UpdateCropType(-1))
                }
            } else {
                Log.e("TAG", "EditPhotoScreen error")
                viewModel.handleEvents(EditPhotoEvent.UpdateCropType(0))
            }
        }

    var colorList by remember { mutableStateOf(mutableListOf<List<Color>>()) }

    LaunchedEffect(key1 = Unit, block = {
        colorList = randomColor()
    })

    if (state.cropType == 2) {
        val cropOptions =
            CropImageContractOptions(state.path, CropImageOptions(imageSourceIncludeCamera = false))
        imageCropLauncher.launch(cropOptions)
    } else
        Scaffold(
            topBar = {
                AppBar(navController = navController, currentPage = currentPage ?: "edit_photo")
            },
            containerColor = Color("#ebebeb".toColorInt()),
            contentColor = Color("#ebebeb".toColorInt())
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                CardPhoto(
                    screenWidth,
                    path = state.path,
                    indexSizeList = state.size,
                    indexShape = state.shapeIndex,
                    cropType = state.cropType,
                    borderColor = if (state.borderColor == -1) listOf(Color.Transparent) else colorList[state.borderColor],
                    cornerSize = state.cornerSize,
                    openMedia = { openPhotoPicker(pickMedia) })

                EditPhotoLayout(
                    path = state.path,
                    indexSize = state.size,
                    indexShape = state.shapeIndex,
                    screenWidth = screenWidth,
                    colorList = colorList,
                    updateSize = { size -> viewModel.handleEvents(EditPhotoEvent.UpdateSize(size)) },
                    updateCropType = { cropType ->
                        viewModel.handleEvents(
                            EditPhotoEvent.UpdateCropType(
                                cropType
                            )
                        )
                    },
                    updateBorderColor = { borderPosition ->
                        viewModel.handleEvents(
                            EditPhotoEvent.UpdateBorderColor(
                                borderPosition
                            )
                        )
                    },
                    updateCorner = { cornerSize ->
                        viewModel.handleEvents(
                            EditPhotoEvent.UpdateCorner(
                                cornerSize
                            )
                        )
                    },
                    updateShape = { index ->
                        viewModel.handleEvents(
                            EditPhotoEvent.UpdateShape(
                                index
                            )
                        )
                    }
                )
            }
        }
}

