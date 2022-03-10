package com.example.meditationui.ui.music.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.meditationui.R
import com.example.meditationui.ui.theme.Rose500

@Composable
fun TitlePlayListBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Playlist")
        Row {
            Icon(
                painter = painterResource(id = R.drawable.ic_shuffle),
                tint = Rose500,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(3.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_repeat),
                tint = Rose500,
                contentDescription = null
            )

        }
    }
}
