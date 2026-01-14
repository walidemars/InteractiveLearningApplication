package com.nikitinsky.interactive.learning.app.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.nikitinsky.interactive.learning.app.data.local.model.KanaSymbolDbModel
import com.nikitinsky.interactive.learning.app.domain.model.KanaType
import kotlinx.coroutines.flow.Flow

@Dao
interface KanaDao {

    @Query("SELECT * FROM kana_symbols WHERE type == :kanaType")
    fun getAllKanaByType(kanaType: KanaType): Flow<List<KanaSymbolDbModel>>

    @Query("SELECT * FROM kana_symbols WHERE levelId == :levelId")
    fun getKanaForLevel(levelId: Int): Flow<List<KanaSymbolDbModel>>

    @Query("SELECT * FROM kana_symbols WHERE id == :kanaId")
    fun getKanaById(kanaId: Int): KanaSymbolDbModel
}