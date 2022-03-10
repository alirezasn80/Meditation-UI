package com.example.meditationui.ui.music


sealed class MusicEvents {
    data class UpdateSeekBarPosition(val position: Float) : MusicEvents()

    data class ClickedMusicButton(val indexItem: Int, val link: String) : MusicEvents()

    object GetMusicPlayList : MusicEvents()
}