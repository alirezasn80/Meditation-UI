package com.example.meditationui.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MusicDto(
    @SerializedName("create_at")
    val create_at: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("title")
    val title: String
)

fun MusicDto.toMusic() = Music(
    id = id,
    link = link,
    title = title,
    img = null
)