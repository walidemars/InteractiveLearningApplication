package com.nikitinsky.interactive.learning.app.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.nikitinsky.interactive.learning.app.data.local.model.LevelDbModel
import com.nikitinsky.interactive.learning.app.domain.model.KanaType
import kotlinx.coroutines.flow.Flow

@Dao
interface LevelDao {

    @Query("SELECT * FROM levels WHERE id == :levelId")
    fun getLevel(levelId: Int): LevelDbModel

    @Query("SELECT * FROM levels WHERE kanaType == :kanaType")
    fun getAllLevelsByType(kanaType: KanaType): Flow<List<LevelDbModel>>
}