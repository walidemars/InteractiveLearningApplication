package com.nikitinsky.interactive.learning.app.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nikitinsky.interactive.learning.app.domain.entity.KanaType

@Entity(tableName = "levels")
data class LevelDbModel(
    @PrimaryKey val id: Int,
    val kanaType: KanaType
)
