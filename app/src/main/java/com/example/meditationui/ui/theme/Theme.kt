package com.example.meditationui.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal

private val DarkColorPalette = darkColors(
    primary = Blue,
    primaryVariant = DarkBlue,
    onPrimary = White,
    secondary = DarkBlue,
    secondaryVariant = Rose100,
    onSecondary = White,
    error = Rose100,
    onError = Rose500,
    background = DarkBlue,
    onBackground = White,
    surface = DarkBlue,
    onSurface = White,
)


@Composable
fun MeditationUITheme(
    content: @Composable() () -> Unit
) {
    val colors = DarkColorPalette


    CompositionLocalProvider(LocalSpacing provides Spacing()) {
        MaterialTheme(
            colors = colors,
            typography = typography,
            shapes = Shapes,
            content = content
        )
    }

}