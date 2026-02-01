package com.nikitinsky.interactive.learning.app.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import com.nikitinsky.interactive.learning.app.domain.entity.KanaType

@Entity(
    tableName = "kana",
    primaryKeys = ["romaji", "kanaType"],
    foreignKeys = [
        ForeignKey(
        entity = LevelDbModel::class,
        parentColumns = ["id"],
        childColumns = ["levelId"],
        )
    ]
)
data class KanaDbModel(
    val japaneseSymbol: String,
    val romaji: String,
    val kanaType: KanaType,
    val levelId: Int,
)