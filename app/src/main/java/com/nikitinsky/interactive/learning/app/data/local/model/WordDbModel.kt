package com.nikitinsky.interactive.learning.app.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val kanji: String?, // написние иероглифами
    val kana: String, // написание каной
    val translation: String, // перевод на английский
    val levelId: Int
)
