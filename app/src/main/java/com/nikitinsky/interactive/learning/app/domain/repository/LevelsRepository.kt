package com.nikitinsky.interactive.learning.app.domain.repository

import com.nikitinsky.interactive.learning.app.domain.model.KanaType
import com.nikitinsky.interactive.learning.app.domain.model.Level
import kotlinx.coroutines.flow.Flow

interface LevelsRepository {

    fun getLevel(levelId: Int): Level

    fun getAllLevelsByType(kanaType: KanaType): Flow<List<Level>>
}