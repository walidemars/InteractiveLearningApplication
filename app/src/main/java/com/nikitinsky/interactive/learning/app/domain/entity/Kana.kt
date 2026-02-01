package com.nikitinsky.interactive.learning.app.domain.entity

data class Kana(
    val japaneseSymbol: String,
    val romaji: String,
    val kanaType: KanaType,
    val levelId: Int,
)

enum class KanaType {
    HIRAGANA,
    KATAKANA
}
