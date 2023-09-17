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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hqnguyen.widgetapp.ui.MainScreen
import com.hqnguyen.widgetapp.ui.page.widget.AddWidgetScreen
import com.hqnguyen.widgetapp.ui.theme.WidgetAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WidgetApp() {
    val navController = rememberNavController()
    val currentPage = navController.currentBackStackEntryAsState().value
    Log.d("WidgetApp", "WidgetApp: $currentPage")
    Scaffold(floatingActionButton = {
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
        composable("main") {
            MainScreen() {
                navigationApp(it, navController)
            }
        }
        composable("add/{type}") { backStackEntry ->
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
        visible = currentPage == "main",
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

fun navigationApp(router: String, navController: NavController) {
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