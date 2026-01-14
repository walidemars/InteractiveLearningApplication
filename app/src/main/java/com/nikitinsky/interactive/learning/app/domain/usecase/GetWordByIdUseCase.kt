package com.nikitinsky.interactive.learning.app.domain.usecase

import com.nikitinsky.interactive.learning.app.domain.model.Word
import com.nikitinsky.interactive.learning.app.domain.repository.KanaRepository
import com.nikitinsky.interactive.learning.app.domain.repository.WordsRepository

class GetWordByIdUseCase(
    private val repository: WordsRepository
) {
    operator fun invoke(wordId: Int): Word {
        return repository.getWordById(wordId)
    }
}