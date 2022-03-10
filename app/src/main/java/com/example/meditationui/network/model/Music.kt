package com.example.meditationui.network.model

import androidx.annotation.DrawableRes

data class Music(
    val id: Int,
    val link: String,
    val title: String,
    @DrawableRes var img: Int?
)