package com.example.meditationui.ui.music.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import androidx.paging.compose.itemsIndexed
import com.example.meditationui.R
import com.example.meditationui.network.model.Music
import com.example.meditationui.ui.music.MusicEvents
import com.example.meditationui.ui.theme.Rose500
import com.example.meditationui.ui.util.MusicIconState
import com.example.meditationui.ui.util.getIcon

@Composable
fun MusicPlayList(
    musicPlayList: LazyPagingItems<Music>,
    musicIconState: MusicIconState,
    events: (MusicEvents) -> Unit,
    seekBarPosition: Float,
    indexItem: Int
) {

    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 60.dp)
    ) {
        itemsIndexed(musicPlayList) { index,music ->

            music?.let { MusicPlayListItem(it, musicIconState, events, indexItem, index) }

            if (index == indexItem)
                SeekBar(
                    seekBarPosition = seekBarPosition,
                    onValueChange = { position ->
                        events(MusicEvents.UpdateSeekBarPosition(position))
                    },
                    thumbColor = Rose500,
                    activeTrackColor = Rose500,
                    inactiveTrackColor = Color.Transparent
                )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}

@Composable
fun MusicPlayListItem(
    music: Music,
    musicIconState: MusicIconState,
    events: (MusicEvents) -> Unit,
    itemIndex: Int,
    index: Int,
) {
    Row(
        Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = music.img ?: R.drawable.img_med_1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(20))
                    .size(65.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(text = music.title)
                Text(text = "Meditation", style = MaterialTheme.typography.subtitle1)
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "favorite icon",
                tint = Color.LightGray
            )
            Spacer(modifier = Modifier.width(10.dp))


            IconButton(onClick = { events(MusicEvents.ClickedMusicButton(index, music.link)) }) {
                Icon(
                    painter = painterResource(if (index == itemIndex) musicIconState.getIcon() else R.drawable.ic_play),
                    contentDescription = "music icon"
                )
            }
        }

    }
}





















