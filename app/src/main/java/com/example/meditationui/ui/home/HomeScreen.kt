package com.example.meditationui.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meditationui.R
import com.example.meditationui.ui.home.components.ChipSection
import com.example.meditationui.ui.home.components.DailySection
import com.example.meditationui.ui.home.components.FeatureGridList
import com.example.meditationui.ui.home.components.TopBarSection
import com.example.meditationui.ui.home.model.Feature
import com.example.meditationui.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination


@ExperimentalFoundationApi
@Composable
@Destination(start = true)
@Preview
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
    ) {
        TopBarSection(name = "mmd", onClick = {})
        ChipSection(chips = listOf("Sweet sleep", "Insomnia", "Depression"))
        DailySection()
        Text(text = "Featured", Modifier.padding(top = 15.dp, start = 15.dp, bottom = 10.dp))
        FeatureGridList(
            features = listOf(
                Feature(
                    title = "Sleep \n meditation",
                    icon = R.drawable.ic_music,
                    color = Color.Blue.copy(0.5F)
                ),
                Feature(
                    title = "Tip for sleeping",
                    icon = R.drawable.ic_sleep,
                    color = BrightGreen
                ),
                Feature(
                    title = "Night island",
                    icon = R.drawable.ic_home,
                    color = Gold
                ),
                Feature(
                    title = "Calming sounds",
                    icon = R.drawable.ic_meditation,
                    color = Rose100
                ),
                Feature(
                    title = "Default",
                    icon = R.drawable.ic_profile,
                    color = Color.Magenta
                ),
                Feature(
                    title = "New item",
                    icon = R.drawable.ic_launcher_foreground,
                    color = Color.LightGray
                ),
            )
        )


    }
}