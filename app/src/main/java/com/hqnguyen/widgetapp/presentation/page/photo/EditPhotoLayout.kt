package com.hqnguyen.widgetapp.presentation.page.photo

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hqnguyen.widgetapp.R
import com.hqnguyen.widgetapp.presentation.page.widget.add.item.SizeEdit
import com.hqnguyen.widgetapp.presentation.page.widget.add.item.listCards
import es.dmoral.toasty.Toasty
import okhttp3.internal.wait

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EditPhotoLayout(
    modifier: Modifier = Modifier,
    path: Uri? = null,
    screenWidth: Dp,
    updateSize: (size: Int) -> Unit,
    updateCropType: (cropType: Int) -> Unit,
) {

    val context = LocalContext.current

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
                    Toasty.info(context, "Please chose your photo.", Toast.LENGTH_SHORT, true)
                        .show()
                    return@SizeEdit
                }
                updateSize(it)
            }
            Spacer(modifier = Modifier.height(16.dp))

            CropEdit {
                if (path == null) {
                    Toasty.info(context, "Please choose your photo.", Toast.LENGTH_SHORT, true)
                        .show()
                    return@CropEdit
                }
                updateCropType(it)
                Log.d("CropEdit", "CropEdit ")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}