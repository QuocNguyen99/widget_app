package com.hqnguyen.widgetapp.ui.page.widget

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
import androidx.compose.ui.unit.dp
import com.hqnguyen.widgetapp.ui.page.widget.item.BackgroundEdit
import com.hqnguyen.widgetapp.ui.page.widget.item.DateEdit
import com.hqnguyen.widgetapp.ui.page.widget.item.SizeEdit
import com.hqnguyen.widgetapp.ui.page.widget.item.TextColorEdit
import com.hqnguyen.widgetapp.ui.page.widget.item.TextSizeEdit
import com.hqnguyen.widgetapp.ui.page.widget.item.TitleEdit

@Composable
fun CardEdit(modifier: Modifier = Modifier, onClickCardSize: (index: Int) -> Unit) {
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
            TitleEdit()
            Spacer(modifier = Modifier.height(16.dp))

            DateEdit()
            Spacer(modifier = Modifier.height(16.dp))

            SizeEdit(onClickCardSize)
            Spacer(modifier = Modifier.height(16.dp))

            BackgroundEdit()
            Spacer(modifier = Modifier.height(16.dp))

            TextSizeEdit()
            Spacer(modifier = Modifier.height(16.dp))

            TextColorEdit()
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
