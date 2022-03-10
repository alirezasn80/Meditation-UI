package com.example.meditationui.ui.music

import ProgressBarState
import androidx.paging.Pager
import com.example.meditationui.network.model.Music
import com.example.meditationui.ui.util.MusicIconState


data class MusicState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val progressBarMusicState: ProgressBarState = ProgressBarState.Idle,
    val musicPlayList: Pager<Int, Music>? = null,
    val totalMusicTime: String = "00:00",
    val seekBarPosition: Float = 0f,
    val currentMusicTime: String = "00:00",
    val musicIconState: MusicIconState = MusicIconState.Play
)