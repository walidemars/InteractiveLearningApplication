package com.nikitinsky.interactive.learning.app.domain.usecase

import com.nikitinsky.interactive.learning.app.domain.model.Word
import com.nikitinsky.interactive.learning.app.domain.repository.KanaRepository

class GetWordByIdUseCase(
    private val repository: KanaRepository
) {
    operator fun invoke(wordId: Int): Word {
        return repository.getWordById(wordId)
    }
}