package com.hqnguyen.widgetapp.presentation.page.widget.add

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.appwidget.AppWidgetProviderInfo
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Scaffold
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
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hqnguyen.widgetapp.R
import com.hqnguyen.widgetapp.data.model.WidgetInfo
import com.hqnguyen.widgetapp.presentation.custom.AppBar
import com.hqnguyen.widgetapp.ui.theme.WidgetAppTheme
import com.hqnguyen.widgetapp.utils.loadImageAndSaveToCache
import com.hqnguyen.widgetapp.utils.openPhotoPicker
import com.hqnguyen.widgetapp.widget_glance.EventWidgetPinnedReceiver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) { uri ->
            Log.d("PhotoPicker", "Selected URI: $uri")
            viewModel.handleEvents(WidgetEvent.UpdateBackground(uri.first()))
        }

    val screenWidth = configuration.screenWidthDp.dp

    val context = LocalContext.current

    LaunchedEffect(key1 = true, block = {
        Log.d(TAG, "id: $id")
        if (id != null && id.toLong() != -1L) {
            viewModel.handleEvents(WidgetEvent.FetchInfoWidget(id.toLong()))
        }
    })

    Scaffold(
        topBar = {
            AppBar(
                currentPage = currentPage ?: "",
                navController = navController,
                textRightButton = "Add Widget",
                onRightButtonClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        loadImageAndSaveToCache(context, state.pathImage.toString()) {
                            viewModel.handleEvents(
                                WidgetEvent.SaveWidget(
                                    widgetInfo = WidgetInfo(
                                        id = System.currentTimeMillis().toString(),
                                        title = state.title,
                                        date = state.date,
                                        size = state.sizeCard,
                                        sizeText = state.textSize,
                                        colorText = state.textColor,
                                        imagePath = it,
                                        defaultImage = state.defaultImage
                                    ),
                                    it
                                )
                            )
                        }
                    }
                }
            )
        }, containerColor = Color("#ebebeb".toColorInt()),
        contentColor = Color("#ebebeb".toColorInt())
    ) { it ->
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
                currentIndexSize = state.sizeCard,
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
            val widgetManager = AppWidgetManager.getInstance(context)
            val widgetProviders =
                widgetManager.getInstalledProvidersForPackage(context.packageName, null)

            widgetProviders.first().pin(context)
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

private fun AppWidgetProviderInfo.pin(context: Context) {
    val successCallback = PendingIntent.getBroadcast(
        context,
        0,
        Intent(context, EventWidgetPinnedReceiver::class.java),
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    AppWidgetManager.getInstance(context).requestPinAppWidget(ComponentName(context.packageName, MyWidgetProvider::class.java.name), null, successCallback)

    val pinnedWidgetIds = AppWidgetManager.getInstance(context).getAppWidgetIds(provider)
//
//    if (pinnedWidgetIds.isNotEmpty()) {
//        val remoteViewsToUse = RemoteViews(context.packageName, R.layout.widget_initial_layout_4_4)
//        val lastWidgetId = pinnedWidgetIds.last()
//        // Xử lý WidgetId tại đây
//        Log.d("TAG", "pinnedWidgetIds: $lastWidgetId")
//
//        AppWidgetManager.getInstance(context).updateAppWidget(lastWidgetId, remoteViewsToUse)
//
//    } else {
//
//    }
}

class MyWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // Cập nhật giao diện Widget
        val remoteViews = RemoteViews(context.packageName, R.layout.widget_initial_layout_4_4)
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews)
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        // Xử lý sự kiện Widget
//        if (intent.action == AppWidgetManager.CL) {
//            val appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)
//            if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
//                // Xử lý click cho Widget
//            }
//        }
    }
}


@Preview
@Composable
fun AddWidgetScreenReview() {
    WidgetAppTheme {
        AddWidgetScreen()
    }
}