package com.nikitinsky.interactive.learning.app.data.repository

import com.nikitinsky.interactive.learning.app.domain.model.KanaSymbol
import com.nikitinsky.interactive.learning.app.domain.model.KanaType
import com.nikitinsky.interactive.learning.app.domain.model.Level
import com.nikitinsky.interactive.learning.app.domain.model.Word
import com.nikitinsky.interactive.learning.app.domain.repository.KanaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

object TestKanaRepositoryImpl : KanaRepository {

    private val kanaListFlow = MutableStateFlow<List<KanaSymbol>>(listOf())
    private val levelListFlow = MutableStateFlow<List<Level>>(listOf())

    override fun getKanaById(kanaId: Int): KanaSymbol {
        return kanaListFlow.value.first { it.id == kanaId }
    }

    override fun getAllKanaByType(kanaType: KanaType): Flow<List<KanaSymbol>> {
        return kanaListFlow.map { kanaList ->
            kanaList.filter { it.type == kanaType }
        }
    }

    override fun getKanaForLevel(levelId: Int): Flow<List<KanaSymbol>> {
        val level = levelListFlow.value.first { it.id == levelId }
        return kanaListFlow.map { kanaList ->
            kanaList.filter {
                level.kanaIds.contains(it.id)
            }
        }
    }
}