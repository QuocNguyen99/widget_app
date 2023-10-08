package com.hqnguyen.widgetapp

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hqnguyen.widgetapp.data.model.NavItem
import com.hqnguyen.widgetapp.ui.MainScreen
import com.hqnguyen.widgetapp.presentation.page.widget.add.AddWidgetScreen
import com.hqnguyen.widgetapp.ui.theme.WidgetAppTheme

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WidgetApp() {
    val navController = rememberNavController()
    val currentPage = navController.currentBackStackEntryAsState().value

    Scaffold(floatingActionButton = {
        FloatingButton(currentPage?.destination?.route, navController)
    }) { padding ->
        Surface(color = Color(0xFFF5F5F5)) {
            NavigationWidgetApp(navController, Modifier.padding(padding), currentPage)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationWidgetApp(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    currentPage: NavBackStackEntry?,
) {
    NavHost(navController = navController, startDestination = "main") {
        composable(NavItem.MAIN.router) {
            MainScreen {
                navigationApp(it, navController)
            }
        }
        composable(NavItem.ADD.router) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toLong()
            AddWidgetScreen(
                id = id,
                modifier = modifier,
                currentPage = currentPage?.destination?.route,
                navController = navController
            )
        }
    }
}

@Composable
fun FloatingButton(currentPage: String? = "", navController: NavController) {
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
            onClick = { navController.navigate("add/-1") },
        ) {
            Icon(
                Icons.Filled.Add,
                " Floating action button.",
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