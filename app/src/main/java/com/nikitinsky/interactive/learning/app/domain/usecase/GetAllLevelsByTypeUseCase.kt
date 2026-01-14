package com.nikitinsky.interactive.learning.app.domain.usecase

import com.nikitinsky.interactive.learning.app.domain.model.KanaType
import com.nikitinsky.interactive.learning.app.domain.model.Level
import com.nikitinsky.interactive.learning.app.domain.repository.KanaRepository
import kotlinx.coroutines.flow.Flow

class GetAllLevelsByTypeUseCase(
    private val repository: KanaRepository
) {
    operator fun invoke(kanaType: KanaType): Flow<List<Level>> {
        return repository.getAllLevelsByType(kanaType)
    }
}