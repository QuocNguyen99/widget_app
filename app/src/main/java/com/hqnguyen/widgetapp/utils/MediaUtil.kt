package com.hqnguyen.widgetapp.utils

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts

fun openPhotoPicker(pickMedia: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>) {
    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
}