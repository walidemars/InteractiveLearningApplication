package com.nikitinsky.interactive.learning.app.presentation.screens.practice

import com.nikitinsky.interactive.learning.app.domain.entity.Kana
import kotlin.random.Random

data class FallingSymbol(
    val kana: Kana,
    val x: Float = Random.nextFloat() * 800f,
    val y: Float = -100f
)
