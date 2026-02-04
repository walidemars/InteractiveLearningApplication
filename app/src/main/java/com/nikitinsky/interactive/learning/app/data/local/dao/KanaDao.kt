package com.nikitinsky.interactive.learning.app.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.nikitinsky.interactive.learning.app.data.local.model.KanaDbModel
import com.nikitinsky.interactive.learning.app.data.local.model.LevelDbModel
import com.nikitinsky.interactive.learning.app.data.local.model.LevelWithKanaDbModel
import com.nikitinsky.interactive.learning.app.domain.entity.KanaType
import kotlinx.coroutines.flow.Flow

@Dao
interface KanaDao {

    @Query("SELECT * FROM kana WHERE levelId == :levelId")
    fun getKanaForLevel(levelId: Int): Flow<List<KanaDbModel>>

    @Query("SELECT * FROM levels WHERE kanaType == :kanaType")
    fun getLevelsByKanaType(kanaType: KanaType): Flow<List<LevelWithKanaDbModel>>

    @Query("SELECT * FROM levels WHERE id == :levelId")
    suspend fun getLevel(levelId: Int): LevelWithKanaDbModel
}