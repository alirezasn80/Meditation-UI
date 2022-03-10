package com.example.meditationui.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import com.example.meditationui.ui.home.HomeScreen
import com.example.meditationui.ui.music.MusicEvents
import com.example.meditationui.ui.music.MusicScreen
import com.example.meditationui.ui.music.MusicViewModel
import com.example.meditationui.ui.navigation.BottomBarScreen
import com.example.meditationui.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalPagingApi
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeditationUITheme {
                val viewModel: MusicViewModel = hiltViewModel()


                val navController = rememberNavController()

                Scaffold(bottomBar = { BottomBar(navController = navController) }) {
                    NavHost(
                        navController = navController,
                        startDestination = BottomBarScreen.Home.route
                    ) {
                        homeScreenComponents()
                        meditateScreenComponents()
                        sleepScreenComponents()
                        musicScreenComponents(viewModel)
                        profileScreenComponents()
                    }

                }
            }
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    selectedIconColor: Color = LocalContentColor.current,
    unSelectedIconColor: Color = LocalContentColor.current.copy(alpha = ContentAlpha.medium)
) {

    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Meditate,
        BottomBarScreen.Music,
        BottomBarScreen.Sleep,
        BottomBarScreen.Profile
    )

    val backStackEntry = navController.currentBackStackEntryAsState()


    BottomNavigation(
        modifier = modifier,
        backgroundColor = backgroundColor,
        elevation = 5.dp
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                backStackEntry = backStackEntry,
                navController = navController,
                selectedIconColor = selectedIconColor,
                unSelectedIconColor = unSelectedIconColor
            )

        }

    }

}

@ExperimentalMaterialApi
@Composable
private fun RowScope.AddItem(
    screen: BottomBarScreen,
    backStackEntry: State<NavBackStackEntry?>,
    navController: NavHostController,
    selectedIconColor: Color,
    unSelectedIconColor: Color
) {
    val selected = screen.route == backStackEntry.value?.destination?.route

    BottomNavigationItem(
        selected = selected,
        onClick = {

            if (!selected)
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
        },
        selectedContentColor = selectedIconColor,
        unselectedContentColor = unSelectedIconColor,
        icon = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Icon(
                    painter = painterResource(id = screen.icon),
                    contentDescription = screen.title,
                    Modifier.size(24.dp)
                )


                if (selected)
                    Text(text = screen.title)
            }
        }
    )

}

@ExperimentalFoundationApi
fun NavGraphBuilder.homeScreenComponents() {
    composable(route = BottomBarScreen.Home.route) {
        HomeScreen()
    }
}

fun NavGraphBuilder.profileScreenComponents() {
    composable(route = BottomBarScreen.Profile.route) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
        )
    }
}

fun NavGraphBuilder.meditateScreenComponents() {
    composable(route = BottomBarScreen.Meditate.route) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Rose500)
        )
    }
}

fun NavGraphBuilder.sleepScreenComponents() {
    composable(route = BottomBarScreen.Sleep.route) {
        Box {}
    }
}

@ExperimentalPagingApi
fun NavGraphBuilder.musicScreenComponents(viewModel: MusicViewModel) {
    viewModel.onTriggerEvent(MusicEvents.GetMusicPlayList)
    composable(route = BottomBarScreen.Music.route) {
        MusicScreen(viewModel)
    }
}