package com.hqnguyen.widgetapp.presentation.page.widget.add

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProviderInfo
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hqnguyen.widgetapp.data.model.WidgetInfo
import com.hqnguyen.widgetapp.presentation.custom.AppBar
import com.hqnguyen.widgetapp.ui.theme.WidgetAppTheme
import com.hqnguyen.widgetapp.widget_glance.EventWidgetPinnedReceiver

@OptIn(ExperimentalMaterial3Api::class)
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
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: $uri")
                viewModel.handleEvents(WidgetEvent.UpdateBackground(uri))
            } else {
                Log.e("PhotoPicker", "No media selected")
            }
        }

    val screenWidth = configuration.screenWidthDp.dp

    val context = LocalContext.current

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
            textRightButton = "Add Widget",
            onRightButtonClick = {
                val widgetManager = AppWidgetManager.getInstance(context)
                val widgetProviders = widgetManager.getInstalledProvidersForPackage(context.packageName, null)
                widgetProviders.first().pin(context)
//                viewModel.handleEvents(
//                    WidgetEvent.SaveWidget(
//                        widgetInfo = WidgetInfo(
//                            id = System.currentTimeMillis().toString(),
//                            title = state.title,
//                            date = state.date,
//                            size = state.sizeCard,
//                            sizeText = state.textSize,
//                            colorText = state.textColor,
//                            imagePath = if (state.pathImage != null) state.pathImage.toString() else null,
//                            defaultImage = state.defaultImage
//                        )
//                    )
//                )
            }
        )
    }) { it ->
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

        if (state.isShowLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {
                Loading()
            }
        }

        if (state.isSaveComplete) {
            AlertDialog(
                containerColor = Color.White, onDismissRequest = { },
                title = {
                    Text(text = "Alert")
                },
                text = {
                    Text(text = "Event is saved. You can see this event in Tab Event.")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            navController?.popBackStack()
                        }
                    ) {
                        Text(text = "OK")
                    }
                })
        }
    }

}

@Composable
fun Loading() {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("loading_lottie.json"))
    LottieAnimation(
        composition, iterations = LottieConstants.IterateForever,
        modifier = Modifier.size(60.dp)
    )
}

fun openPhotoPicker(pickMedia: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>) {
    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
}

private fun AppWidgetProviderInfo.pin(context: Context) {
    val successCallback = PendingIntent.getBroadcast(
        context,
        0,
        Intent(context, EventWidgetPinnedReceiver::class.java),
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    AppWidgetManager.getInstance(context).requestPinAppWidget(provider, null, successCallback)
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun AddWidgetScreenReview() {
    WidgetAppTheme {
        AddWidgetScreen()
    }
}