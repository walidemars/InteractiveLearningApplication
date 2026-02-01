package com.nikitinsky.interactive.learning.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikitinsky.interactive.learning.app.data.local.dao.KanaDao
import com.nikitinsky.interactive.learning.app.data.local.model.KanaDbModel
import com.nikitinsky.interactive.learning.app.data.local.model.LevelDbModel

@Database(
    entities = [KanaDbModel::class, LevelDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class KanaDatabase: RoomDatabase() {
    abstract fun kanaDao(): KanaDao
}