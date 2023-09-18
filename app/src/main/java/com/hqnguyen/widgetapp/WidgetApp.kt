package com.hqnguyen.widgetapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hqnguyen.widgetapp.data.NavItem
import com.hqnguyen.widgetapp.ui.MainScreen
import com.hqnguyen.widgetapp.ui.page.widget.AddWidgetScreen
import com.hqnguyen.widgetapp.ui.theme.WidgetAppTheme
import kotlin.math.log

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WidgetApp() {
    val navController = rememberNavController()
    val currentPage = navController.currentBackStackEntryAsState().value

    Scaffold(topBar = {
        AppBar(currentPage?.destination?.route, navController)
    }, floatingActionButton = {
        FloatingButton(currentPage?.destination?.route)
    }) {
        Surface(color = Color(0xFFF5F5F5)) {
            NavigationWidgetApp(navController)
        }
    }

}

@Composable
fun NavigationWidgetApp(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "main") {
        composable(NavItem.MAIN.router) {
            MainScreen {
                navigationApp(it, navController)
            }
        }
        composable(NavItem.ADD.router) { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type")
            AddWidgetScreen(
                type = type,
                onNavigation = { navigationApp(it, navController) },
                onBack = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun FloatingButton(currentPage: String? = "") {
    AnimatedVisibility(
        visible = currentPage == NavItem.MAIN.router,
        enter = slideInVertically(animationSpec = tween(durationMillis = 1000)) { it } + fadeIn(
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutVertically(animationSpec = tween(durationMillis = 1000)) { it } + fadeOut(
            animationSpec = tween(durationMillis = 1000)
        )) {
        FloatingActionButton(
            containerColor = Color(0xFF6ac5fe),
            contentColor = Color.White,
            shape = CircleShape,
            onClick = { },
        ) {
            Icon(
                Icons.Filled.Add,
                " Floating action button.",
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(currentPage: String? = "", navController: NavHostController) {
    val navItem = NavItem.findEnumByRouter(currentPage)
    if (currentPage != "main") {
        Surface(shadowElevation = 9.dp) {
            CenterAlignedTopAppBar(
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
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "Settings"
                        )
                    }
                },
//                modifier = Modifier.height(60.dp)
            )
        }
    }
}

fun navigationApp(router: String, navController: NavController) {
    Log.d("navigationApp", "navigationApp: $router")
    if (router.isNotEmpty()) {
        navController.navigate(router)
    } else {
        Log.e("navigationApp", "error: router is not defined")
    }
}


@Preview
@Composable
fun WidgetAppPreview() {
    WidgetAppTheme {
        WidgetApp()
    }
}