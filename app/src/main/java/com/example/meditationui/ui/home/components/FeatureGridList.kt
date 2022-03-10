package com.example.meditationui.ui.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.meditationui.ui.home.model.Feature


@ExperimentalFoundationApi
@Composable
fun FeatureGridList(
    cellNumber: Int = 2,
    features: List<Feature>
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(cellNumber),
        contentPadding = PaddingValues(start = 15.dp, end = 15.dp, bottom = 100.dp)
    ) {
        items(features) {
            FeatureItem(feature = it)
        }

    }

}

@Composable
fun FeatureItem(
    feature: Feature,
    onClick: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .padding(6.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(feature.color)
            .aspectRatio(1f)
            .padding(vertical = 5.dp, horizontal = 16.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .weight(1f)
        ) {
            Text(text = feature.title)
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .weight(1F)
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        ) {
            Icon(
                painter = painterResource(id = feature.icon),
                contentDescription = "Music",
                modifier = Modifier.size(24.dp)
            )

            Button(onClick = { onClick() }) {
                Text(text = "Start")
            }

        }

    }


}