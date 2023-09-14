package com.hqnguyen.widgetapp

import android.util.Log
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hqnguyen.widgetapp.ui.bottom_sheet.PlanBottomSheet
import com.hqnguyen.widgetapp.ui.main.ButtonCustomLeft
import com.hqnguyen.widgetapp.ui.main.ButtonCustomRight
import com.hqnguyen.widgetapp.ui.theme.WidgetAppTheme

enum class HeaderItems(title: String) {
    HOME("Home"), EVENT("Event"), REMIND("Remind"),
}

enum class HeaderSubItems(title: String, icon: ImageVector) {
    DEFAULT("Default", Icons.Filled.FavoriteBorder),
    PLAN("Plan", Icons.Filled.FavoriteBorder),
    BIRTHDAY("Birthday", Icons.Filled.FavoriteBorder),
    SETTING("Setting", Icons.Filled.Settings),
}

@Composable
fun WidgetApp() {
    Surface(color = Color(0xFFFFEED5)) {
        val selectedHeaderState = remember { mutableStateOf(HeaderItems.HOME) }
        val selectedSubHeaderState = remember { mutableStateOf(HeaderSubItems.DEFAULT) }

        Column {
            Header(modifier = Modifier.background(Color.Red), selectedHeaderState.value) { it ->
                val enumMap = HeaderItems.values().associateBy { it.name }
                Log.d("WidgetApp", "selectedHeaderState: ${enumMap[it]}")
                selectedHeaderState.value = enumMap[it]!!
            }

            if (selectedHeaderState.value == HeaderItems.HOME) {
                SubHeader(modifier = Modifier.background(Color.Red)) {
                    val enumMap = HeaderSubItems.values().associateBy { it.name }
                    Log.d("WidgetApp", "selectedHeaderState: ${enumMap[it]}")
                    selectedSubHeaderState.value = enumMap[it]!!
                }
            }
        }

        if (selectedSubHeaderState.value != HeaderSubItems.DEFAULT)
            PlanBottomSheet {
                selectedSubHeaderState.value = HeaderSubItems.DEFAULT
            }
    }
}

@Composable
fun Header(
    modifier: Modifier = Modifier,
    selectedHeaderState: HeaderItems,
    onClickHeaderItem: (item: String) -> Unit
) {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ButtonCustomLeft(
            modifier = modifier,
            title = HeaderItems.HOME.name,
            color = Color.Red,
            colorContainer = if (selectedHeaderState == HeaderItems.HOME) Color(0xFFFDD497) else Color(
                0xFFFFEED5
            ),
            onClickHeaderItem = onClickHeaderItem
        )
        ButtonCustomLeft(
            modifier = modifier,
            title = HeaderItems.EVENT.name,
            color = Color.Red,
            colorContainer = if (selectedHeaderState == HeaderItems.EVENT) Color(0xFFFDD497) else Color(
                0xFFFFEED5
            ),
            onClickHeaderItem = onClickHeaderItem
        )
        ButtonCustomLeft(
            modifier = modifier,
            title = HeaderItems.REMIND.name,
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
    onClickSubHeaderItem: (item: String) -> Unit
) {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ButtonCustomRight(
            modifier = modifier,
            title = HeaderSubItems.PLAN.name,
            color = Color.Red,
            colorContainer = Color(0xFFFFEED5),
            onClickSubHeaderItem = onClickSubHeaderItem
        )
        ButtonCustomRight(
            modifier = modifier,
            title = HeaderSubItems.BIRTHDAY.name,
            color = Color.Red,
            colorContainer = Color(0xFFFFEED5),
            onClickSubHeaderItem = onClickSubHeaderItem
        )
        ButtonCustomRight(
            modifier = modifier,
            title = HeaderSubItems.SETTING.name,
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