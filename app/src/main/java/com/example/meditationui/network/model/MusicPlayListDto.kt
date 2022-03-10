package com.example.meditationui.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class MusicPlayListDto(
    @SerializedName("count_page")
    val count_page: Int,
    @SerializedName("music_list")
    val music_list: List<MusicDto>
)