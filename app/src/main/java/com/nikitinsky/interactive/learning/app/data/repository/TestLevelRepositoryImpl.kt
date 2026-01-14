package com.nikitinsky.interactive.learning.app.data.repository

import com.nikitinsky.interactive.learning.app.domain.model.KanaSymbol
import com.nikitinsky.interactive.learning.app.domain.model.KanaType
import com.nikitinsky.interactive.learning.app.domain.model.Level
import com.nikitinsky.interactive.learning.app.domain.model.Word
import com.nikitinsky.interactive.learning.app.domain.repository.KanaRepository
import com.nikitinsky.interactive.learning.app.domain.repository.LevelsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

object TestLevelRepositoryImpl : LevelsRepository {

    private val levelListFlow = MutableStateFlow<List<Level>>(listOf())

    override fun getLevel(levelId: Int): Level {
        return levelListFlow.value.first { it.id == levelId }
    }

    override fun getAllLevelsByType(kanaType: KanaType): Flow<List<Level>> {
        return levelListFlow.map { levelList ->
            levelList.filter { it.kanaType == kanaType }
        }
    }
}