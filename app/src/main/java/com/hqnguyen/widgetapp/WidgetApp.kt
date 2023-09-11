package com.hqnguyen.widgetapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hqnguyen.widgetapp.ui.main.ButtonHeader
import com.hqnguyen.widgetapp.ui.main.ButtonSubHeader
import com.hqnguyen.widgetapp.ui.theme.WidgetAppTheme

enum class HeaderItems(title: String) {
    HOME("Home"), EVENT("Event"), REMIND("Remind"),
}

enum class HeaderSubItems(title: String, icon: ImageVector) {
    PLAN("Plan", Icons.Filled.FavoriteBorder),
    BIRTHDAY("Birthday", Icons.Filled.FavoriteBorder),
    SETTING("Setting", Icons.Filled.Settings),
}

@Composable
fun WidgetApp() {
    Surface(color = Color(0xFFFFEED5)) {
        val selectedHeaderState = remember { mutableStateOf(HeaderItems.HOME) }
        Column {
            Header(modifier = Modifier.background(Color.Red), selectedHeaderState.value) {
                selectedHeaderState.value = it
            }

            if (selectedHeaderState.value == HeaderItems.HOME) {
                SubHeader(modifier = Modifier.background(Color.Red)) {
                    when (it) {
                        HeaderSubItems.PLAN -> {}
                        HeaderSubItems.BIRTHDAY -> {}
                        HeaderSubItems.SETTING -> {}
                    }
                }
            }
        }
    }
}

@Composable
fun Header(
    modifier: Modifier = Modifier,
    selectedHeaderState: HeaderItems,
    onClickHeaderItem: (item: HeaderItems) -> Unit
) {
    Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.Center) {
        ButtonHeader(
            modifier = modifier,
            title = HeaderItems.HOME,
            color = Color.Red,
            colorContainer = if (selectedHeaderState == HeaderItems.HOME) Color(0xFFFDD497) else Color(
                0xFFFFEED5
            ),
            onClickHeaderItem = onClickHeaderItem
        )
        ButtonHeader(
            modifier = modifier,
            title = HeaderItems.EVENT,
            color = Color.Red,
            colorContainer = if (selectedHeaderState == HeaderItems.EVENT) Color(0xFFFDD497) else Color(
                0xFFFFEED5
            ),
            onClickHeaderItem = onClickHeaderItem
        )
        ButtonHeader(
            modifier = modifier,
            title = HeaderItems.REMIND,
            color = Color.Red,
            colorContainer = if (selectedHeaderState == HeaderItems.REMIND) Color(0xFFFDD497) else Color(
                0xFFFFEED5
            ),
            onClickHeaderItem = onClickHeaderItem
        )
    }
}

@Composable
fun SubHeader(
    modifier: Modifier = Modifier,
    onClickSubHeaderItem: (item: HeaderSubItems) -> Unit
) {
    Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.Center) {
        ButtonSubHeader(
            modifier = modifier,
            title = HeaderSubItems.PLAN,
            color = Color.Red,
            colorContainer = Color(0xFFFFEED5),
            onClickSubHeaderItem = onClickSubHeaderItem
        )
        ButtonSubHeader(
            modifier = modifier,
            title = HeaderSubItems.BIRTHDAY,
            color = Color.Red,
            colorContainer = Color(0xFFFFEED5),
            onClickSubHeaderItem = onClickSubHeaderItem
        )
        ButtonSubHeader(
            modifier = modifier,
            title = HeaderSubItems.SETTING,
            color = Color.Red,
            colorContainer = Color(0xFFFFEED5),
            onClickSubHeaderItem = onClickSubHeaderItem
        )
    }
}

@Preview
@Composable
fun WidgetAppPreview() {
    WidgetAppTheme {
        WidgetApp()
    }
}