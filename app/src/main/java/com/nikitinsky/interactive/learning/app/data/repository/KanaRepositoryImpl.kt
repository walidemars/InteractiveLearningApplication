package com.nikitinsky.interactive.learning.app.data.repository

import com.nikitinsky.interactive.learning.app.data.local.dao.KanaDao
import com.nikitinsky.interactive.learning.app.data.mapper.toEntities
import com.nikitinsky.interactive.learning.app.data.mapper.toEntity
import com.nikitinsky.interactive.learning.app.domain.model.KanaSymbol
import com.nikitinsky.interactive.learning.app.domain.model.KanaType
import com.nikitinsky.interactive.learning.app.domain.repository.KanaRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class KanaRepositoryImpl @Inject constructor(
    private val kanaDao: KanaDao
) : KanaRepository {
    override fun getKanaById(kanaId: Int): KanaSymbol {
        return kanaDao.getKanaById(kanaId).toEntity()
    }

    override fun getAllKanaByType(kanaType: KanaType): Flow<List<KanaSymbol>> {
        return kanaDao.getAllKanaByType(kanaType).map { it.toEntities() }
    }

    override fun getKanaForLevel(levelId: Int): Flow<List<KanaSymbol>> {
        return kanaDao.getKanaForLevel(levelId).map { it.toEntities() }
    }
}