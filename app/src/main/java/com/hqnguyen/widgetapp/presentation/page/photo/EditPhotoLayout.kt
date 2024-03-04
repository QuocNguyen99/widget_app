package com.hqnguyen.widgetapp.presentation.page.photo

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hqnguyen.widgetapp.R
import com.hqnguyen.widgetapp.presentation.page.widget.add.item.SizeEdit
import com.hqnguyen.widgetapp.presentation.page.widget.add.item.listCards

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EditPhotoLayout(path: String, screenWidth: Dp, modifier: Modifier = Modifier) {

    val pagerState = rememberPagerState(pageCount = {
        listCards.size
    })

    val context = LocalContext.current

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            SizeEdit() {
                if (path.isEmpty()) {
                    Toast.makeText(context, "Please select a photo", Toast.LENGTH_LONG).show()
                    return@SizeEdit
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}