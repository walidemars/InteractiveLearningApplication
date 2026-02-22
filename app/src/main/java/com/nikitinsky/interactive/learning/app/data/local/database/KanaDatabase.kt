package com.nikitinsky.interactive.learning.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikitinsky.interactive.learning.app.data.local.dao.KanaDao
import com.nikitinsky.interactive.learning.app.data.local.model.KanaDbModel
import com.nikitinsky.interactive.learning.app.data.local.model.LevelDbModel
import com.nikitinsky.interactive.learning.app.data.local.model.WordDbModel

@Database(
    entities = [KanaDbModel::class, LevelDbModel::class, WordDbModel::class],
    version = 2,
    exportSchema = false
)
abstract class KanaDatabase: RoomDatabase() {
    abstract fun kanaDao(): KanaDao
}