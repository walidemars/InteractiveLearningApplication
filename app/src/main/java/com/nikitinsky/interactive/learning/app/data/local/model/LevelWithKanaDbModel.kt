package com.nikitinsky.interactive.learning.app.data.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class LevelWithKanaDbModel(
    @Embedded
    val levelDbModel: LevelDbModel,
    @Relation(
        parentColumn = "id",
        entityColumn = "levelId"
    )
    val kanaContent: List<KanaDbModel>
)