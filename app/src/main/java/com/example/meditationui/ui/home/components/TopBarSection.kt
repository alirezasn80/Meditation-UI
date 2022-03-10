package com.example.meditationui.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.meditationui.ui.theme.White

@Composable
fun TopBarSection(
    name: String,
    onClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {

            Text(
                text = "Good morning $name",
                style = MaterialTheme.typography.body1
            )

            Text(
                text = "We wish you have good day!",
                style = MaterialTheme.typography.subtitle1
            )
        }

        Icon(
            Icons.Rounded.Search,
            "Search",
            modifier = Modifier
                .size(30.dp)
                .clickable { onClick() },
            tint = White
        )
    }

}