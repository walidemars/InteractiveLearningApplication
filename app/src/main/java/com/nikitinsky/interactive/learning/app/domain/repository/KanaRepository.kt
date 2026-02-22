package com.nikitinsky.interactive.learning.app.domain.repository

import com.nikitinsky.interactive.learning.app.domain.entity.Kana
import com.nikitinsky.interactive.learning.app.domain.entity.KanaType
import com.nikitinsky.interactive.learning.app.domain.entity.Level
import com.nikitinsky.interactive.learning.app.domain.entity.Word
import kotlinx.coroutines.flow.Flow

interface KanaRepository {

    fun getKanaForLevel(levelId: Int): Flow<List<Kana>>

    fun getLevelsByKanaType(kanaType: KanaType): Flow<List<Level>>

    suspend fun getLevel(levelId: Int): Level

    fun getWordsForLevel(levelId: Int): Flow<List<Word>>
}