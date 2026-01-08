package com.nikitinsky.interactive.learning.app.domain.model

data class JapaneseChar(
    val id: Int,
    val value: String, // japanese
    val romaji: String, // england
    val type: CharType // HIRAGANA or KATAKANA
)

enum class CharType { HIRAGANA, KATAKANA }
