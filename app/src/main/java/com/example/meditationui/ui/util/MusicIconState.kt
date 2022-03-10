package com.example.meditationui.ui.util

import com.example.meditationui.R

sealed class MusicIconState {
    object Play : MusicIconState()
    object Pause : MusicIconState()
}

fun MusicIconState.getIcon() =
    when (this) {
        MusicIconState.Pause -> R.drawable.ic_pause
        MusicIconState.Play -> R.drawable.ic_play
    }
