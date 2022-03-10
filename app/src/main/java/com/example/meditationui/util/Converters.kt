package com.example.meditationui.util

import java.util.concurrent.TimeUnit


fun milliSecondsToTimer(millis: Long) = String.format(
    "%02d:%02d",
    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(
        TimeUnit.MILLISECONDS.toHours(
            millis
        )
    ),
    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
        TimeUnit.MILLISECONDS.toMinutes(
            millis
        )
    )
)
