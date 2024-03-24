package com.hqnguyen.widgetapp.presentation.page.photo.edit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.NotInterested
import androidx.compose.material.icons.filled.WifiProtectedSetup
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hqnguyen.widgetapp.utils.convertTimeToText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeIntervalEdit(selectedIndex: Int = 0, updateTimeInterval: (index: Int) -> Unit) {
    val listTimeInterval = listOf(0, 30, 60, 3600, 28800, 86400)

    var isShowDialog by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .padding(16.dp, 0.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true)
            ) {
                isShowDialog = true
            }, verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(weight = 1f)) {
            Text(text = "Select Interval", fontWeight = FontWeight.Bold)
            Text(text = selectedIndex.convertTimeToText(listTimeInterval), fontSize = 12.sp)
        }

        Column {
            Image(imageVector = Icons.Default.WifiProtectedSetup, contentDescription = "")
        }
    }

    if (isShowDialog) {
        ModalBottomSheet(
            containerColor = Color.White,
            onDismissRequest = { isShowDialog = false },

            ) {
            Text(
                text = "Choose time",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            LazyColumn(modifier = Modifier
                .background(Color.White)
                .padding(8.dp, 16.dp), content = {
                items(listTimeInterval.size) {
                    Column {
                        Row(
                            modifier = Modifier
                                .padding(16.dp, 0.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple(bounded = true)
                                ) {
                                    updateTimeInterval(it)
                                    isShowDialog = false
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(end = 8.dp)
                            ) {
                                Icon(
                                    imageVector = if (listTimeInterval[it] == 0) Icons.Default.NotInterested else Icons.Default.AccessTime,
                                    tint = if (selectedIndex == it) Color.Black else Color.Gray,
                                    contentDescription = ""
                                )
                            }
                            Column(modifier = Modifier.weight(weight = 1f)) {
                                Text(
                                    text = it.convertTimeToText(listTimeInterval),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = if (selectedIndex == it) Color.Black else Color.Gray
                                )
                            }
                        }
                        if (it < listTimeInterval.lastIndex)
                            HorizontalDivider(
                                thickness = 1.dp,
                                color = Color.LightGray,
                                modifier = Modifier.padding(8.dp, 8.dp)
                            )
                    }
                }
            })
        }
    }
}
