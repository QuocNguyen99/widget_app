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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hqnguyen.widgetapp.R
import com.hqnguyen.widgetapp.data.TypeTemplate
import com.hqnguyen.widgetapp.ui.page.widget.item.CardImage
import com.hqnguyen.widgetapp.ui.page.widget.item.listCards
import com.hqnguyen.widgetapp.ui.theme.WidgetAppTheme
import org.w3c.dom.Text

@Composable
fun AddWidgetScreen(
    type: String? = "",
    onNavigation: (router: String) -> Unit = {},
    onBack: () -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val defaultTextSize: Float = 9F

    val screenWidth = configuration.screenWidthDp.dp

    var currentTitle: String by rememberSaveable {
        mutableStateOf("Who's birthday?")
    }

    var currentDate: Long by rememberSaveable {
        mutableLongStateOf(System.currentTimeMillis())
    }

    var currentSize: Int by rememberSaveable {
        mutableIntStateOf(0)
    }

    val sizeText: Float = when (currentSize) {
        0 -> 14F
        1 -> 12F
        2 -> 9F
        else -> defaultTextSize
    }

    var currentTextSize: Float by rememberSaveable {
        mutableFloatStateOf(sizeText)
    }

    var currentColor: Int by rememberSaveable {
        mutableIntStateOf(Color.White.toArgb())
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
            CardImage(
                screenWidth = screenWidth,
                indexSizeList = currentSize,
                currentTitle = currentTitle,
                currentDate = currentDate,
                currentSizeText = currentTextSize,
                currentColorText = currentColor
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        CardEdit(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            currentTextSize = currentTextSize,
            currentTextColor = currentColor,
            updateCurrentTitle = { currentTitle = it },
            updateCurrentDate = { currentDate = it },
            updateCurrentTexSize = { currentTextSize = it },
            updateCurrentTextColor = { currentColor = it.toArgb() },
            onClickCardSize = { currentSize = it }
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