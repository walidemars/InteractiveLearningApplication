package com.nikitinsky.interactive.learning.app.domain.usecase

import com.nikitinsky.interactive.learning.app.domain.model.Level
import com.nikitinsky.interactive.learning.app.domain.repository.KanaRepository

class GetLevelUseCase(
    private val repository: KanaRepository
) {
    operator fun invoke(levelId: Int): Level {
        return repository.getLevel(levelId)
    }
}