package com.hqnguyen.widgetapp.presentation.page.widget.add

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hqnguyen.widgetapp.presentation.page.widget.add.item.BackgroundEdit
import com.hqnguyen.widgetapp.presentation.page.widget.add.item.DateEdit
import com.hqnguyen.widgetapp.presentation.page.widget.add.item.SizeEdit
import com.hqnguyen.widgetapp.presentation.page.widget.add.item.TextColorEdit
import com.hqnguyen.widgetapp.presentation.page.widget.add.item.TextSizeEdit
import com.hqnguyen.widgetapp.presentation.page.widget.add.item.TitleEdit

@Composable
fun CardEdit(
    modifier: Modifier = Modifier,
    currentIndexSize:Int = 0,
    currentTextSize: Float = 9F,
    currentTextColor: Int = Color.White.toArgb(),
    updateCurrentTitle: (title: String) -> Unit,
    updateCurrentDate: (milliseconds: Long) -> Unit,
    updateCurrentTexSize: (size: Float) -> Unit,
    updateCurrentTextColor: (color: Color) -> Unit,
    onClickCardSize: (index: Int) -> Unit,
    openPhotoPicker: () -> Unit,
    onClickDefaultPhoto: () -> Unit
) {
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
            TitleEdit(updateCurrentTitle)
            Spacer(modifier = Modifier.height(16.dp))

            DateEdit(updateCurrentDate)
            Spacer(modifier = Modifier.height(16.dp))

            SizeEdit(indexSize = currentIndexSize,onClickCardSize)
            Spacer(modifier = Modifier.height(16.dp))

            BackgroundEdit(openPhotoPicker, onClickDefaultPhoto)
            Spacer(modifier = Modifier.height(16.dp))

            TextSizeEdit(currentTextSize, updateCurrentTexSize)
            Spacer(modifier = Modifier.height(16.dp))

            TextColorEdit(currentTextColor, updateCurrentTextColor)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
