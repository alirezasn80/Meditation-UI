package com.example.meditationui.ui.music

import android.media.MediaPlayer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.example.meditationui.interactors.music.GetMusicPlayList
import com.example.meditationui.interactors.music.PrepareMusic
import com.example.meditationui.ui.util.DataState
import com.example.meditationui.ui.util.MusicIconState
import com.example.meditationui.ui.util.debug
import com.example.meditationui.util.milliSecondsToTimer

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val prepareMusic: PrepareMusic,
    private val getMusicPlayList: GetMusicPlayList
) : ViewModel() {
    lateinit var job: Job
    var indexItemClicked = -1

    val state: MutableState<MusicState> = mutableStateOf(MusicState())
    private val mediaPlayer: MediaPlayer = MediaPlayer()

    @ExperimentalPagingApi
    fun onTriggerEvent(events: MusicEvents) {
        when (events) {

            is MusicEvents.UpdateSeekBarPosition -> {
                updateSeekBarPosition(events.position)
            }

            is MusicEvents.ClickedMusicButton -> {
                if (events.indexItem == indexItemClicked) {
                    updateMusicTask()
                } else {
                    debug("index item != indexItemClickd")
                    indexItemClicked = events.indexItem
                    mediaPlayer.reset()
                    prepareMusic(events.link)
                }
            }

            MusicEvents.GetMusicPlayList -> {
                getMusicPlayList()
            }
        }
    }

    @ExperimentalPagingApi
    private fun getMusicPlayList() {
        getMusicPlayList.execute().onEach { dataState ->
            when (dataState) {
                is DataState.Data -> {
                    state.value = state.value.copy(musicPlayList = dataState.data)
                }
                is DataState.Loading -> state.value =
                    state.value.copy(progressBarState = dataState.progressBarState)
            }
        }.launchIn(viewModelScope)
    }

    private fun updateMusicTask() {
        if (mediaPlayer.isPlaying)
            pauseMusic()
        else
            playMusic()

    }

    private fun updateSeekBarPosition(position: Float) {
        state.value = state.value.copy(seekBarPosition = position)
    }

    private fun getTotalMusicTime() {
        state.value =
            state.value.copy(totalMusicTime = milliSecondsToTimer(mediaPlayer.duration.toLong()))
    }

    private fun updateSeekBar() {
        job = viewModelScope.launch(IO) {
            while (true) {
                if (mediaPlayer.isPlaying) {

                    state.value =
                        state.value.copy(
                            seekBarPosition = (mediaPlayer.currentPosition.toFloat() / mediaPlayer.duration.toFloat())
                        )

                    state.value =
                        state.value.copy(currentMusicTime = milliSecondsToTimer(mediaPlayer.currentPosition.toLong()))
                }
                delay(1000)
            }
        }
    }

    private fun prepareMusic(link: String) {
        if (mediaPlayer.isPlaying)
            mediaPlayer.pause()

        if (state.value.seekBarPosition != 0f)
            state.value = state.value.copy(seekBarPosition = 0f)

        changeMusicIcon(MusicIconState.Pause)

        prepareMusic.execute(mediaPlayer, link).onEach { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                    state.value =
                        state.value.copy(progressBarMusicState = dataState.progressBarState)
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        if (it == "Success") {
                            mediaPlayer.start()
                            getTotalMusicTime()
                            updateSeekBar()
                        }
                    }

                }
            }
        }.launchIn(viewModelScope)
    }

    private fun changeMusicIcon(musicIconState: MusicIconState) {
        state.value = state.value.copy(musicIconState = musicIconState)
    }

    private fun pauseMusic() {
        mediaPlayer.pause()
        changeMusicIcon(MusicIconState.Play)
        job.cancel()
    }

    private fun playMusic() {
        mediaPlayer.start()
        changeMusicIcon(MusicIconState.Pause)
        updateSeekBar()
    }
}