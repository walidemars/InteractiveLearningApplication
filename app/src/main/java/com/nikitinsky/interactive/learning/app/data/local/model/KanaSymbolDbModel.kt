package com.nikitinsky.interactive.learning.app.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nikitinsky.interactive.learning.app.domain.model.KanaType

@Entity(tableName = "kana_symbols")
data class KanaSymbolDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val japaneseChar: String, // japanese
    val romaji: String, // england
    val type: KanaType, // HIRAGANA or KATAKANA
    val levelId: Int
)