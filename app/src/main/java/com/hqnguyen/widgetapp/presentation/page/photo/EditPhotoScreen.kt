package com.hqnguyen.widgetapp.presentation.page.photo

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hqnguyen.widgetapp.presentation.custom.AppBar
import com.hqnguyen.widgetapp.utils.openPhotoPicker

@Composable
fun EditPhotoScreen(currentPage: String? = "", navController: NavHostController? = null) {

    val configuration = LocalConfiguration.current
    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: $uri")
            } else {
                Log.e("PhotoPicker", "No media selected")
            }
        }

    val screenWidth = configuration.screenWidthDp.dp
    var path: String = ""

    Scaffold(topBar = {
        AppBar(navController = navController, currentPage = currentPage ?: "edit_photo")
    }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            CardPhoto(screenWidth, openMedia = { openPhotoPicker(pickMedia) })

            EditPhotoLayout(path,screenWidth)
        }
    }
}