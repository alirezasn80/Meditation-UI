package com.example.meditationui.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.meditationui.ui.theme.*


@Composable
fun DailySection(
    itemClick: () -> Unit = {},
    musicClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { itemClick() }
            .clip(RoundedCornerShape(10.dp))
            .background(Rose500)
            .padding(vertical = 20.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = "Daily thought", style = MaterialTheme.typography.body1)
            Text(text = "meditation . 3-10 min", style = MaterialTheme.typography.subtitle1)
        }

        Icon(
            imageVector = Icons.Rounded.PlayArrow,
            contentDescription = "play",
            tint = White,
            modifier = Modifier
                .size(
                    40.dp
                )
                .background(Blue, CircleShape)
                .padding(4.dp)
                .clickable { musicClick() }
        )
    }

}