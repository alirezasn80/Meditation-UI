package com.example.meditationui.ui.music.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SeekBar(
    seekBarPosition: Float,
    onValueChange: (Float) -> Unit,
    thumbColor: Color = MaterialTheme.colors.primary,
    activeTrackColor: Color = MaterialTheme.colors.primary,
    inactiveTrackColor: Color = activeTrackColor.copy(alpha = SliderDefaults.InactiveTrackAlpha)
) {
    Slider(
        modifier = Modifier.fillMaxWidth(),
        value = seekBarPosition,
        onValueChange = {
            onValueChange(it)
        },
        colors = SliderDefaults.colors(
            thumbColor = thumbColor,
            activeTrackColor = activeTrackColor,
            inactiveTrackColor = inactiveTrackColor
        )
    )

}