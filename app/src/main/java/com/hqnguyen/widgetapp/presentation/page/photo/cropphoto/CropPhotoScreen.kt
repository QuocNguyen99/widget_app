//package com.hqnguyen.widgetapp.presentation.page.photo.cropphoto
//
////import android.content.Context
////import android.net.Uri
////import android.util.Log
////import androidx.compose.foundation.background
////import androidx.compose.foundation.layout.Box
////import androidx.compose.foundation.layout.Column
////import androidx.compose.foundation.layout.fillMaxSize
////import androidx.compose.foundation.layout.fillMaxWidth
////import androidx.compose.runtime.Composable
////import androidx.compose.runtime.LaunchedEffect
////import androidx.compose.runtime.collectAsState
////import androidx.compose.runtime.getValue
////import androidx.compose.runtime.mutableStateOf
////import androidx.compose.runtime.remember
////import androidx.compose.runtime.setValue
////import androidx.compose.ui.Alignment
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.graphics.Color
////import androidx.compose.ui.graphics.ImageBitmap
////import androidx.compose.ui.graphics.asImageBitmap
////import androidx.compose.ui.platform.LocalContext
////import androidx.compose.ui.platform.LocalDensity
////import androidx.compose.ui.unit.dp
////import androidx.core.net.toUri
////import com.bumptech.glide.Glide
////import com.smarttoolfactory.cropper.ImageCropper
////import com.smarttoolfactory.cropper.model.OutlineType
////import com.smarttoolfactory.cropper.model.RectCropShape
////import com.smarttoolfactory.cropper.settings.CropDefaults
////import com.smarttoolfactory.cropper.settings.CropOutlineProperty
////import kotlinx.coroutines.Dispatchers
////import kotlinx.coroutines.flow.Flow
////import kotlinx.coroutines.flow.collect
////import kotlinx.coroutines.flow.first
////import kotlinx.coroutines.flow.firstOrNull
////import kotlinx.coroutines.flow.flow
////import kotlinx.coroutines.flow.flowOn
////import kotlinx.coroutines.withContext
//
//@Composable
//fun CropPhotoScreen(path: String = "") {
//    val handleSize: Float = LocalDensity.current.run { 20.dp.toPx() }
//
//    val cropProperties by remember {
//        mutableStateOf(
//            CropDefaults.properties(
//                cropOutlineProperty = CropOutlineProperty(
//                    OutlineType.Rect,
//                    RectCropShape(0, "Rect")
//                ),
//                handleSize = handleSize
//            )
//        )
//    }
//    val cropStyle by remember { mutableStateOf(CropDefaults.style()) }
//
//    var imgBitmap: ImageBitmap? by remember {
//        mutableStateOf(null)
//    }
//
//    val crop by remember { mutableStateOf(true) }
//    val context = LocalContext.current
//
//    LaunchedEffect(key1 = path, block = {
//        withContext(Dispatchers.IO) {
//            val bitmap = Glide.with(context)
//                .asBitmap()
//                .load(path)
//                .submit()
//                .get()
//            imgBitmap = bitmap.asImageBitmap()
//        }
//    })
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.DarkGray),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(modifier = Modifier.fillMaxSize()) {
//            Log.d("TAG", "CropPhotoScreen: ${imgBitmap == null}")
//            imgBitmap?.let {
//                ImageCropper(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .weight(1f),
//                    imageBitmap = it,
//                    contentDescription = "Image Cropper",
//                    cropStyle = cropStyle,
//                    cropProperties = cropProperties,
//                    crop = crop,
//                    onCropStart = {},
//                    onCropSuccess = {},
//                )
//            }
//        }
//    }
//}