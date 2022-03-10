package com.example.meditationui.ui.home.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Feature(
    val title: String,
    @DrawableRes val icon: Int,
    val color: Color
)
