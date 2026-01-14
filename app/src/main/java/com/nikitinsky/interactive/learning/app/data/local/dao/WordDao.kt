package com.nikitinsky.interactive.learning.app.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.nikitinsky.interactive.learning.app.data.local.model.WordDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query("SELECT * FROM words WHERE id == :wordId")
    fun getWordById(wordId: Int): WordDbModel

    @Query("SELECT * FROM words WHERE levelId == :levelId")
    fun getWordsForLevel(levelId: Int): Flow<List<WordDbModel>>
}