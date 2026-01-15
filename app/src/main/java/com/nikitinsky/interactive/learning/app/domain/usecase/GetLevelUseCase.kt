package com.nikitinsky.interactive.learning.app.domain.usecase

import com.nikitinsky.interactive.learning.app.domain.model.Level
import com.nikitinsky.interactive.learning.app.domain.repository.LevelsRepository
import javax.inject.Inject

class GetLevelUseCase @Inject constructor (
    private val repository: LevelsRepository
) {
    operator fun invoke(levelId: Int): Level {
        return repository.getLevel(levelId)
    }
}