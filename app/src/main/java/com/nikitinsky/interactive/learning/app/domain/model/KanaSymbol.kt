package com.nikitinsky.interactive.learning.app.domain.model

data class KanaSymbol(
    val id: Int,
    val japaneseChar: String, // japanese
    val romaji: String, // england
    val type: KanaType, // HIRAGANA or KATAKANA
    val levelId: Int
)

enum class KanaType { HIRAGANA, KATAKANA }
