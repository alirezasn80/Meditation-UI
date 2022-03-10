package com.example.meditationui.ui.home.components


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.meditationui.ui.theme.Blue

@Composable
fun ChipSection(
    chips: List<String>,
    selectedColor: Color = Blue,
    unSelectedColor: Color = Blue.copy(.3F),
    onClick: () -> Unit = {}
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }

    LazyRow {
        itemsIndexed(chips) { index, name ->
            val color by animateColorAsState(
                targetValue = if (selectedChipIndex == index) {
                    selectedColor
                } else {
                    unSelectedColor
                },
                animationSpec = tween(durationMillis = 150, easing = LinearEasing)
            )
            Box(
                Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color)
                    .clickable {
                        selectedChipIndex = index
                        onClick()
                    }
                    .padding(15.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = name, style = MaterialTheme.typography.body1)
            }
        }
    }

}
