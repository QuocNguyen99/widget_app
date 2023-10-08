package com.hqnguyen.widgetapp.presentation.page.widget.add

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hqnguyen.widgetapp.R
import com.hqnguyen.widgetapp.data.model.WidgetInfo
import com.hqnguyen.widgetapp.presentation.custom.AppBar
import com.hqnguyen.widgetapp.ui.theme.WidgetAppTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddWidgetScreen(
    viewModel: WidgetViewModel = hiltViewModel(),
    id: Long? = -1L,
    currentPage: String? = "",
    navController: NavHostController? = null,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val TAG = "AddWidgetScreen"
    val state by viewModel.state.collectAsState()
    val configuration = LocalConfiguration.current
    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: ${uri}")
                viewModel.handleEvents(WidgetEvent.UpdateBackground(uri))
            } else {
                Log.e("PhotoPicker", "No media selected")
            }
        }
    val defaultTextSize: Float = 9F

    val screenWidth = configuration.screenWidthDp.dp

    LaunchedEffect(key1 = true, block = {
        Log.d(TAG, "id: $id")
        if (id != null && id.toLong() != -1L) {
            viewModel.handleEvents(WidgetEvent.FetchInfoWidget(id.toLong()))
        }
    })

    Scaffold(topBar = {
        AppBar(
            currentPage = currentPage ?: "",
            navController = navController,
            textRightButton = "Save",
            onRightButtonClick = {
                viewModel.handleEvents(
                    WidgetEvent.SaveWidget(
                        widgetInfo = WidgetInfo(
                            id = System.currentTimeMillis().toString(),
                            title = state.title,
                            date = state.date,
                            size = state.sizeCard,
                            sizeText = state.textSize,
                            colorText = state.textColor,
                            imagePath = if (state.pathImage != null) state.pathImage.toString() else null,
                            defaultImage = state.defaultImage
                        )
                    )
                )
            }
        )
    }) { it ->
        if (state.isShowLoading) {
            Loading()
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenWidth / 2),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CardImage(
                    screenWidth = screenWidth,
                    indexSizeList = state.sizeCard,
                    currentTitle = state.title,
                    currentDate = state.date,
                    currentSizeText = state.textSize,
                    currentColorText = state.textColor,
                    defaultImage = state.defaultImage,
                    localPath = state.pathImage,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            CardEdit(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                currentTextSize = state.textSize,
                currentTextColor = state.textColor,
                updateCurrentTitle = { viewModel.handleEvents(WidgetEvent.UpdateTitle(it)) },
                updateCurrentDate = { viewModel.handleEvents(WidgetEvent.UpdateDate(it)) },
                updateCurrentTexSize = { viewModel.handleEvents(WidgetEvent.UpdateFontSize(it)) },
                updateCurrentTextColor = { viewModel.handleEvents(WidgetEvent.UpdateColorText(it.toArgb())) },
                onClickCardSize = { viewModel.handleEvents(WidgetEvent.UpdateSize(it)) },
                openPhotoPicker = { openPhotoPicker(pickMedia) },
                onClickDefaultPhoto = {
                    Log.d(TAG, "AddWidgetScreen: onClickDefaultPhoto")
                    viewModel.handleEvents(WidgetEvent.UpdateDefaultPhoto)
                }
            )
        }
    }

}

@Composable
fun Loading() {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("loading_lottie.json"))
    LottieAnimation(composition)
}


fun openPhotoPicker(pickMedia: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>) {
    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun AddWidgetScreenReview() {
    WidgetAppTheme {
        AddWidgetScreen()
    }
}