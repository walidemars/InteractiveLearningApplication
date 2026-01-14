package com.nikitinsky.interactive.learning.app.domain.usecase

import com.nikitinsky.interactive.learning.app.domain.model.KanaSymbol
import com.nikitinsky.interactive.learning.app.domain.repository.KanaRepository
import kotlinx.coroutines.flow.Flow

class GetKanaForLevelUseCase(
    private val repository: KanaRepository
) {
    operator fun invoke(levelId: Int): Flow<List<KanaSymbol>> {
        return repository.getKanaForLevel(levelId)
    }
}