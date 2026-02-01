package com.nikitinsky.interactive.learning.app.domain.entity

data class Level(
    val id: Int,
    val kanaList: List<Kana>,
    val kanaType: KanaType
)
