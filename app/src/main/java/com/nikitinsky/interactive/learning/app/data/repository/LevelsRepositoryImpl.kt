package com.nikitinsky.interactive.learning.app.data.repository

import com.nikitinsky.interactive.learning.app.data.local.dao.LevelDao
import com.nikitinsky.interactive.learning.app.data.mapper.toEntities
import com.nikitinsky.interactive.learning.app.data.mapper.toEntity
import com.nikitinsky.interactive.learning.app.domain.model.KanaType
import com.nikitinsky.interactive.learning.app.domain.model.Level
import com.nikitinsky.interactive.learning.app.domain.repository.LevelsRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LevelsRepositoryImpl @Inject constructor(
    private val levelsDao: LevelDao
) : LevelsRepository {
    override fun getLevel(levelId: Int): Level {
        return levelsDao.getLevel(levelId).toEntity()
    }

    override fun getAllLevelsByType(kanaType: KanaType): Flow<List<Level>> {
        return levelsDao.getAllLevelsByType(kanaType).map { it.toEntities() }
    }
}