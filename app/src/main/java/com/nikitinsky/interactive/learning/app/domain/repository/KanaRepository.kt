package com.nikitinsky.interactive.learning.app.domain.repository

import com.nikitinsky.interactive.learning.app.domain.model.KanaSymbol
import com.nikitinsky.interactive.learning.app.domain.model.KanaType
import kotlinx.coroutines.flow.Flow

interface KanaRepository {

    fun getKanaById(kanaId: Int): KanaSymbol

    fun getAllKanaByType(kanaType: KanaType): Flow<List<KanaSymbol>>

    fun getKanaForLevel(levelId: Int): Flow<List<KanaSymbol>>
}