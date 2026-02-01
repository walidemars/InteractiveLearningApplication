package com.nikitinsky.interactive.learning.app.data.repository

import com.nikitinsky.interactive.learning.app.data.local.dao.KanaDao
import com.nikitinsky.interactive.learning.app.data.mapper.toKanaEntities
import com.nikitinsky.interactive.learning.app.data.mapper.toLevelEntities
import com.nikitinsky.interactive.learning.app.domain.entity.Kana
import com.nikitinsky.interactive.learning.app.domain.entity.KanaType
import com.nikitinsky.interactive.learning.app.domain.entity.Level
import com.nikitinsky.interactive.learning.app.domain.repository.KanaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class KanaRepositoryImpl @Inject constructor(
    private val kanaDao: KanaDao
) : KanaRepository {
    override fun getKanaForLevel(levelId: Int): Flow<List<Kana>> {
        return kanaDao.getKanaForLevel(levelId).map { it.toKanaEntities() }
    }

    override fun getLevelsByKanaType(kanaType: KanaType): Flow<List<Level>> {
        return kanaDao.getLevelsByKanaType(kanaType).map { it.toLevelEntities() }
    }
}