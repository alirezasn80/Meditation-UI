package com.example.meditationui.ui.music.components

import ProgressBarState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.meditationui.ui.music.MusicEvents
import com.example.meditationui.ui.theme.Rose500
import com.example.meditationui.ui.util.MusicIconState
import com.example.meditationui.ui.util.getIcon


fun Modifier.advancedShadow(
    color: Color = Color.Black,
    alpha: Float = 0f,
    cornersRadius: Dp = 0.dp,
    shadowBlurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = drawBehind {

    val shadowColor = color.copy(alpha = alpha).toArgb()
    val transparentColor = color.copy(alpha = 0f).toArgb()

    drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor
        frameworkPaint.setShadowLayer(
            shadowBlurRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            shadowColor
        )
        it.drawRoundRect(
            0f,
            0f,
            this.size.width,
            this.size.height,
            cornersRadius.toPx(),
            cornersRadius.toPx(),
            paint
        )
    }
}

@Composable
fun IconMusicPlayer(
    musicIconState: MusicIconState,
    seekBarPosition: Float,
    progressBarState: ProgressBarState
) {
    Box {
        Icon(
            painter = painterResource(musicIconState.getIcon()),
            "music icon",
            modifier = Modifier
                .size(60.dp)
                .offset(y = (-30).dp),
            tint = Rose500
        )
        if (progressBarState is ProgressBarState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(58.7.dp)
                    .offset(y = (-30).dp),
                color = Color.White
            )
        }
        CircularProgressIndicator(
            progress = seekBarPosition,
            modifier = Modifier
                .size(58.7.dp)
                .offset(y = (-30).dp),
            color = Color.White
        )
    }
}