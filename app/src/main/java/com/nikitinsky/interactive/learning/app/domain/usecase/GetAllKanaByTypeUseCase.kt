package com.nikitinsky.interactive.learning.app.domain.usecase

import com.nikitinsky.interactive.learning.app.domain.model.KanaSymbol
import com.nikitinsky.interactive.learning.app.domain.model.KanaType
import com.nikitinsky.interactive.learning.app.domain.repository.KanaRepository
import kotlinx.coroutines.flow.Flow

class GetAllKanaByTypeUseCase(
    private val repository: KanaRepository
) {
    operator fun invoke(kanaType: KanaType): Flow<List<KanaSymbol>> {
        return repository.getAllKanaByType(kanaType)
    }
}