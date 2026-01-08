package com.nikitinsky.interactive.learning.app.domain.model

data class Level(
    val id: Int,
    val title: String,
    val japaneseChars: String, //List<JapaneseChar> = listOf(),
    val isUnlocked: Boolean = false
)