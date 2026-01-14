package com.nikitinsky.interactive.learning.app.domain.model

data class Level(
    val id: Int,
    val title: String,
    val kanaIds: List<Int>, // каны входящие в уровень
    val wordIds: List<Int>, // слова входящие в уровень
    val isUnlocked: Boolean = false,
    val kanaType: KanaType
)