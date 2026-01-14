package com.nikitinsky.interactive.learning.app.domain.usecase

import com.nikitinsky.interactive.learning.app.domain.model.Word
import com.nikitinsky.interactive.learning.app.domain.repository.KanaRepository
import com.nikitinsky.interactive.learning.app.domain.repository.WordsRepository
import kotlinx.coroutines.flow.Flow

class GetWordsForLevelUseCase(
    private val repository: WordsRepository
) {
    operator fun invoke(levelId: Int): Flow<List<Word>> {
        return repository.getWordsForLevel(levelId)
    }
}