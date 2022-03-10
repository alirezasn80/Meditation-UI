package com.example.meditationui.ui.util

import ProgressBarState

sealed class DataState<T> {

    data class Data<T>(
        val data: T? = null,
    ) : DataState<T>()

    data class Loading<T>(
        val progressBarState: ProgressBarState = ProgressBarState.Idle
    ) : DataState<T>()
}