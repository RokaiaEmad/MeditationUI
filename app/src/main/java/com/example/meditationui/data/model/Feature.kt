package com.example.meditationui.data.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Feature(
    val title: String,
    val color: Color,
    @DrawableRes val iconId: Int
)
