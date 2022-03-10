package com.example.meditationui.interactors.music

import android.media.MediaPlayer
import android.util.Log
import com.example.meditationui.ui.util.DataState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.lang.Exception


class PrepareMusic {

    fun execute(mediaPlayer: MediaPlayer, link: String) = flow<DataState<String>> {
        emit(DataState.Loading(ProgressBarState.Loading))

        try {
            withContext(IO) {
                mediaPlayer.setDataSource(link)
                mediaPlayer.prepare()
            }
            emit(DataState.Data("Success"))
        } catch (e: Exception) {
            emit(DataState.Data("Error : \n ${e.message}"))
        } finally {
            emit(DataState.Loading(ProgressBarState.Idle))
        }
    }
}