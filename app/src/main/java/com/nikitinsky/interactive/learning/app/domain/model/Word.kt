package com.nikitinsky.interactive.learning.app.domain.model

data class Word(
    val id: Int,
    val kanji: String?, // написние иероглифами
    val kana: String, // написание каной
    val translation: String, // перевод на английский
    val levelId: Int
)
