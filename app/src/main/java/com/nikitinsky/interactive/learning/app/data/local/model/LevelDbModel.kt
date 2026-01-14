package com.nikitinsky.interactive.learning.app.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nikitinsky.interactive.learning.app.domain.model.KanaType

@Entity(tableName = "levels")
data class LevelDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val kanaIds: List<Int>, // каны входящие в уровень
    val wordIds: List<Int>, // слова входящие в уровень
    val isUnlocked: Boolean = false,
    val kanaType: KanaType
)