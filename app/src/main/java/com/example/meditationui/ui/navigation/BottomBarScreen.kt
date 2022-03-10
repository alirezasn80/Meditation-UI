package com.example.meditationui.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.example.meditationui.R


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int
) {

    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.ic_home
    )

    object Meditate : BottomBarScreen(
        route = "meditate",
        title = "Meditate",
        icon = R.drawable.ic_meditation
    )

    object Sleep : BottomBarScreen(
        route = "sleep",
        title = "Sleep",
        icon = R.drawable.ic_sleep
    )

    object Music : BottomBarScreen(
        route = "music",
        title = "Music",
        icon = R.drawable.ic_music
    )

    object Profile : BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = R.drawable.ic_profile
    )

}
