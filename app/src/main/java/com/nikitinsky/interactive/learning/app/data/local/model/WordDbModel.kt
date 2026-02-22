package com.nikitinsky.interactive.learning.app.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "words",
    foreignKeys = [
        ForeignKey(
            entity = LevelDbModel::class,
            parentColumns = ["id"],
            childColumns = ["levelId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class WordDbModel(
    val word: String,
    @PrimaryKey val romaji: String,
    val levelId: Int
)
