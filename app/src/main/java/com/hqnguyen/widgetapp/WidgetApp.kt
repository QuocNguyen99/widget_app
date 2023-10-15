package com.hqnguyen.widgetapp

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hqnguyen.widgetapp.data.model.NavItem
import com.hqnguyen.widgetapp.presentation.page.event.EventScreen
import com.hqnguyen.widgetapp.presentation.page.history.HistoryScreen
import com.hqnguyen.widgetapp.presentation.page.home.HomeScreen
import com.hqnguyen.widgetapp.presentation.page.widget.add.AddWidgetScreen
import com.hqnguyen.widgetapp.ui.theme.WidgetAppTheme

sealed class ScreenBottomBar(val router: String, @StringRes val name: Int, @DrawableRes val resSelectedId: Int, @DrawableRes val resUnselectedId: Int) {
    object Home : ScreenBottomBar("home", R.string.home_screen, R.drawable.home_selected, R.drawable.home_unselected)
    object Event : ScreenBottomBar("event", R.string.event_screen, R.drawable.event_selected, R.drawable.event_unselected)
    object Setting : ScreenBottomBar("setting", R.string.setting_screen, R.drawable.setting_selected, R.drawable.setting_unselected)
}

val listScreensBottomBar = listOf(ScreenBottomBar.Home, ScreenBottomBar.Event, ScreenBottomBar.Setting)

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WidgetApp() {
    val navController = rememberNavController()
    val currentPage = navController.currentBackStackEntryAsState().value

    Scaffold(floatingActionButton = {
        FloatingButton(currentPage?.destination?.route, navController)
    }, bottomBar = { BottomNavBar(navController, currentPage?.destination?.route) })
    { padding ->
        Surface(color = Color(0xFFF5F5F5)) {
            NavigationWidgetApp(navController, Modifier.padding(padding), currentPage)
        }
    }
}

@Composable
fun BottomNavBar(navController: NavController, currentRouter: String?) {
    val isShowBottomBar = listScreensBottomBar.find { currentRouter == it.router } != null
    AnimatedVisibility(visible = isShowBottomBar, enter = slideInVertically(animationSpec = tween(durationMillis = 1000)) { it } + fadeIn(
        animationSpec = tween(durationMillis = 200)
    ), exit = slideOutVertically(animationSpec = tween(durationMillis = 1000)) { it } + fadeOut(
        animationSpec = tween(durationMillis = 200)
    )) {
        NavigationBar(
            modifier = Modifier
                .height(70.dp)
                .graphicsLayer {
                    clip = true
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                    shadowElevation = 2.2f
                }, containerColor = Color.White
        ) {
            listScreensBottomBar.forEach { screen ->
                val isSelected = currentRouter == screen.router
                NavigationBarItem(
                    selected = currentRouter == screen.router,
                    onClick = { navController.navigate(screen.router) },
//                label = { Text(text = stringResource(id = screen.name), style = TextStyle(fontSize = 11.sp)) },
//                alwaysShowLabel = isSelected,
                    icon = {
                        Crossfade(
                            targetState = isSelected, label = "", animationSpec = tween(200)
                        ) { isSelected ->
                            if (isSelected)
                                Image(
                                    painter =
                                    painterResource(id = screen.resSelectedId),
                                    contentDescription = "",
                                    modifier = Modifier.size(24.dp)
                                )
                            else
                                Image(
                                    painter =
                                    painterResource(id = screen.resUnselectedId),
                                    contentDescription = "",
                                    modifier = Modifier.size(24.dp)
                                )
                        }
                    }
                )
            }
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
    NavHost(navController = navController, startDestination = ScreenBottomBar.Home.router) {
        composable(ScreenBottomBar.Home.router) {
            HomeScreen {
                navigationApp(it, navController)
            }
        }

        composable(ScreenBottomBar.Event.router) {
            EventScreen() {
                navigationApp(it, navController)
            }
        }

        composable(ScreenBottomBar.Setting.router) {
            HistoryScreen() {
                navigationApp(it, navController)
            }
        }

        composable(NavItem.ADD.router) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toLong()
            AddWidgetScreen(
                id = id, modifier = modifier, currentPage = currentPage?.destination?.route, navController = navController
            )
        }
    }
}

@Composable
fun FloatingButton(currentPage: String? = "", navController: NavController) {
    val isShowFAB = listScreensBottomBar.find { currentPage == it.router } != null
    AnimatedVisibility(visible = isShowFAB, enter = slideInVertically(animationSpec = tween(durationMillis = 1000)) { it } + fadeIn(
        animationSpec = tween(durationMillis = 1000)
    ), exit = slideOutVertically(animationSpec = tween(durationMillis = 1000)) { it } + fadeOut(
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun WidgetAppPreview() {
    WidgetAppTheme {
        WidgetApp()
    }
}