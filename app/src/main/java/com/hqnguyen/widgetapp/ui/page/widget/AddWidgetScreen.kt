package com.hqnguyen.widgetapp.ui.page.widget

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hqnguyen.widgetapp.R
import com.hqnguyen.widgetapp.data.TypeTemplate
import com.hqnguyen.widgetapp.ui.page.widget.item.BackgroundEdit
import com.hqnguyen.widgetapp.ui.page.widget.item.DateEdit
import com.hqnguyen.widgetapp.ui.page.widget.item.SizeEdit
import com.hqnguyen.widgetapp.ui.page.widget.item.TextColorEdit
import com.hqnguyen.widgetapp.ui.page.widget.item.TextSizeEdit
import com.hqnguyen.widgetapp.ui.page.widget.item.TitleEdit
import com.hqnguyen.widgetapp.ui.page.widget.item.listCards
import com.hqnguyen.widgetapp.ui.theme.WidgetAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddWidgetScreen(
    type: String? = "",
    onNavigation: (router: String) -> Unit = {},
    onBack: () -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp.dp

    val currentSize = rememberSaveable {
        mutableIntStateOf(0)
    }

    LaunchedEffect(key1 = true, block = {
        Log.d("AddWidgetScreen", "type: $type")
    })

    Column(
        modifier = modifier
            .fillMaxSize(),
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
            Card(elevation = CardDefaults.cardElevation(8.dp)) {
                Image(
                    painter = painterResource(id = R.mipmap.bg_wedding),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(screenWidth / listCards[currentSize.intValue].height)
                        .height(screenWidth / listCards[currentSize.intValue].width)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        CardEdit(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { currentSize.intValue = it }
    }

    type?.let {
        Log.d("AddWidgetScreen", "TypeTemplate.from(type): ${TypeTemplate.from(type)} ")
        when (TypeTemplate.from(type)) {
            else -> Unit
        }
    }
}

@Composable
fun ItemEditBackground(title: String, @DrawableRes idBackground: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = idBackground),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = TextStyle(fontWeight = FontWeight.Light, fontSize = 12.sp, color = Color.Gray)
        )
    }
}

@Preview
@Composable
fun AddWidgetScreenReview() {
    WidgetAppTheme {
        AddWidgetScreen()
    }
}