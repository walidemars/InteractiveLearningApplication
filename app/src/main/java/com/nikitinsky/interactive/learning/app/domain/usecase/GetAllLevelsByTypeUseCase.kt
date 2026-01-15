package com.nikitinsky.interactive.learning.app.domain.usecase

import com.nikitinsky.interactive.learning.app.domain.model.KanaType
import com.nikitinsky.interactive.learning.app.domain.model.Level
import com.nikitinsky.interactive.learning.app.domain.repository.LevelsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllLevelsByTypeUseCase @Inject constructor (
    private val repository: LevelsRepository
) {
    operator fun invoke(kanaType: KanaType): Flow<List<Level>> {
        return repository.getAllLevelsByType(kanaType)
    }
}