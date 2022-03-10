package com.example.meditationui.ui.music

import ProgressBarState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.meditationui.R
import com.example.meditationui.ui.music.components.IconMusicPlayer

import com.example.meditationui.ui.music.components.BottomRoundedImage
import com.example.meditationui.ui.music.components.MusicPlayList
import com.example.meditationui.ui.music.components.TitlePlayListBar
import com.example.meditationui.ui.util.debug
import com.ramcosta.composedestinations.annotation.Destination

@ExperimentalPagingApi
@Destination
@Composable
fun MusicScreen(
    viewModel: MusicViewModel
) {

    viewModel.apply {
        ProgressBar(state.value.progressBarState)

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BottomRoundedImage(image = R.drawable.img_med_1)

            IconMusicPlayer(
                state.value.musicIconState,
                state.value.seekBarPosition,
                state.value.progressBarMusicState
            )

            TitlePlayListBar()

            state.value.musicPlayList?.flow?.let { data ->
                MusicPlayList(
                    musicPlayList = data.collectAsLazyPagingItems(),
                    musicIconState = state.value.musicIconState,
                    events = { onTriggerEvent(it) },
                    seekBarPosition = state.value.seekBarPosition,
                    indexItemClicked
                )
            }
        }
    }


}

@Composable
fun ProgressBar(progressBarState: ProgressBarState) {
    debug("progressBarState -> $progressBarState")
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (progressBarState is ProgressBarState.Loading)
            CircularProgressIndicator(color = Color.White)
    }

}


/*@Composable
private fun SeekBarComponents(
    events: (MusicEvents) -> Unit,
    progressBarState: ProgressBarState,
    @DrawableRes iconPlay: Int
) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = { *//*TODO*//* }) {
                Icon(Icons.Default.ArrowBack, null)
            }

            IconButton(onClick = { events(MusicEvents.ClickedMusicButton()) }) {
                Icon(painter = painterResource(id = iconPlay), null)
            }

            IconButton(onClick = { *//*TODO*//* }) {
                Icon(Icons.Default.ArrowForward, null)
            }
        }
        if (progressBarState is ProgressBarState.Loading)
            LinearProgressIndicator()

    }
}*/








