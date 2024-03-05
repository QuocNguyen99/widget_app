package com.hqnguyen.widgetapp.presentation.custom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.hqnguyen.widgetapp.data.model.NavItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    currentPage: String? = "",
    navController: NavHostController?,
    textRightButton: String? = null,
    onRightButtonClick: () -> Unit = {}
) {
    if (navController == null) return
    val navItem = NavItem.findEnumByRouter(currentPage)
    Surface(shadowElevation = 9.dp) {
        CenterAlignedTopAppBar(
            colors = TopAppBarColors(
                containerColor = Color.White,
                scrolledContainerColor = Color.White,
                navigationIconContentColor = Color.Black,
                titleContentColor = Color.Black,
                actionIconContentColor = Color.Black
            ),
            title = {
                Text(
                    navItem?.title ?: "",
                    maxLines = 1,
                    overflow = TextOverflow.Visible,
                    fontSize = 18.sp
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            actions = {
                if (!textRightButton.isNullOrBlank()) {
                    Text(
                        text = textRightButton,
                        color = Color(0xFF6ac5fe),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(bounded = true)
                            ) {
                                onRightButtonClick()
                            })
                }
            },
        )
    }
}