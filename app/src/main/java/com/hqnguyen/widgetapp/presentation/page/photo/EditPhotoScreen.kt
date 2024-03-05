package com.hqnguyen.widgetapp.presentation.page.photo

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.Glide
import com.hqnguyen.widgetapp.presentation.custom.AppBar
import com.hqnguyen.widgetapp.presentation.page.photo.cropphoto.CropPhotoScreen
import com.hqnguyen.widgetapp.utils.openPhotoPicker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File

@Composable
fun EditPhotoScreen(
    currentPage: String? = "",
    navController: NavHostController? = null,
    viewModel: EditPhotoViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    val configuration = LocalConfiguration.current
    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            Log.d("EditPhotoScreen", "Selected URI: $uri")
            uri?.let {
                viewModel.handleEvents(EditPhotoEvent.UpdatePhoto(it))
                return@rememberLauncherForActivityResult
            }
            Log.e("EditPhotoScreen", "No media selected")
        }

    val screenWidth = configuration.screenWidthDp.dp

    Scaffold(
        topBar = {
            AppBar(navController = navController, currentPage = currentPage ?: "edit_photo")
        },
        containerColor = Color("#ebebeb".toColorInt()),
        contentColor = Color("#ebebeb".toColorInt())
    ) {
        if (state.cropType == 2) {
            CropPhotoScreen(
                imgBitmap = state.path?.let { path ->
                    convertToImageBitmap(
                        LocalContext.current,
                        path
                    ).collectAsState(
                        initial = null
                    ).value
                }
            )
        } else
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
                    cropType = state.cropType,
                    openMedia = { openPhotoPicker(pickMedia) })

                EditPhotoLayout(
                    path = state.path,
                    screenWidth = screenWidth,
                    updateSize = { size -> viewModel.handleEvents(EditPhotoEvent.UpdateSize(size)) },
                    updateCropType = { cropType ->
                        viewModel.handleEvents(
                            EditPhotoEvent.UpdateCropType(
                                cropType
                            )
                        )
                    }
                )
            }
    }
}

fun convertToImageBitmap(context: Context, path: Uri): Flow<ImageBitmap?> {
    return flow {
        val bitmap = Glide.with(context)
            .asBitmap()
            .load(path)
            .submit()
            .get()

        if (bitmap != null)
            emit(bitmap.asImageBitmap())
        else
            emit(null)
    }
        .flowOn(Dispatchers.IO)
}