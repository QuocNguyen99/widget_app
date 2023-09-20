package com.hqnguyen.widgetapp.ui.page.widget

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import com.hqnguyen.widgetapp.ui.theme.WidgetAppTheme

data class CardDefault(val height: Int, val width: Int)

val listCards = listOf(
    CardDefault(2, 2),
    CardDefault(2, 4),
    CardDefault(4, 4),
)

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
        ) {
            Text(
                text = "Size",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ItemEditSize(
                    0,
                    "4x4",
                    listCards[listCards.size - 1].width,
                    listCards[listCards.size - 1].height
                ) { onClickCardSize(it) }

                Spacer(modifier = Modifier.width(22.dp))
                ItemEditSize(
                    1,
                    "2x4",
                    listCards[listCards.size - 2].width,
                    listCards[listCards.size - 2].height
                ) { onClickCardSize(it) }

                Spacer(modifier = Modifier.width(22.dp))
                ItemEditSize(
                    2,
                    "2x2",
                    listCards[listCards.size - 3].width,
                    listCards[listCards.size - 3].height
                ) { onClickCardSize(it) }

            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Background",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ItemEditBackground("Background", R.mipmap.bg_wedding)
                Spacer(modifier = Modifier.width(16.dp))
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(40.dp)
                        .background(Color.LightGray)
                )
                Spacer(modifier = Modifier.width(22.dp))
                ItemEditBackground("Effect", R.mipmap.bg_birthday)
                Spacer(modifier = Modifier.width(22.dp))
                ItemEditBackground("Default", R.mipmap.bg_study)
            }
        }
    }
}

@Composable
fun ItemEditSize(
    index: Int = 0,
    title: String,
    width: Int,
    height: Int,
    onClick: (index: Int) -> Unit
) {
    val defaultSize = 15.dp
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            color = Color.LightGray,
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .width(defaultSize * width)
                .height(defaultSize * height)
                .clip(
                    RoundedCornerShape(4.dp)
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true)
                ) { onClick(index) }
        ) {

        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = TextStyle(fontWeight = FontWeight.Light, fontSize = 12.sp, color = Color.Gray)
        )
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

// Làm sau
//@Composable
//fun SwitchBackground() {
//    val cardState = rememberPagerState { listCards.size }
//
//    HorizontalPager(
//        state = cardState,
//        pageSpacing = 16.dp,
//        modifier = Modifier
//            .height(screenWidth / 2)
//            .fillMaxWidth(),
//        contentPadding = PaddingValues(horizontal = screenWidth / 4)
//    ) { page ->
//        Card {
//            Image(
//                painter = painterResource(id = R.mipmap.bg_wedding),
//                contentDescription = "",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .width(screenWidth / listCards[page].width)
//                    .height(screenWidth / listCards[page].height)
//            )
//        }
//    }
//}

@Preview
@Composable
fun AddWidgetScreenReview() {
    WidgetAppTheme {
        AddWidgetScreen()
    }
}