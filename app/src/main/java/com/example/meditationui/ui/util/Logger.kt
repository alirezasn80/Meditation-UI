package com.example.meditationui.ui.util

import android.util.Log


fun debug(message: String, tag: String = "") {
    Log.i("AppDebug", "$tag : $message")
}