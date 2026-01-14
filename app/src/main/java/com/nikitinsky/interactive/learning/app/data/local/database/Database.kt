package com.nikitinsky.interactive.learning.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikitinsky.interactive.learning.app.data.local.dao.KanaDao
import com.nikitinsky.interactive.learning.app.data.local.dao.LevelDao
import com.nikitinsky.interactive.learning.app.data.local.dao.WordDao
import com.nikitinsky.interactive.learning.app.data.local.model.KanaSymbolDbModel
import com.nikitinsky.interactive.learning.app.data.local.model.LevelDbModel
import com.nikitinsky.interactive.learning.app.data.local.model.WordDbModel

@Database(
    entities = [KanaSymbolDbModel::class, WordDbModel::class, LevelDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class Database: RoomDatabase() {

    abstract fun kanaDao(): KanaDao

    abstract fun wordDao(): WordDao

    abstract fun levelDao(): LevelDao
}