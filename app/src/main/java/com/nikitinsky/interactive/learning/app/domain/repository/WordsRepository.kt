package com.nikitinsky.interactive.learning.app.domain.repository

import com.nikitinsky.interactive.learning.app.domain.model.Word
import kotlinx.coroutines.flow.Flow

interface WordsRepository {

    fun getWordById(wordId: Int): Word

    fun getWordsForLevel(levelId: Int): Flow<List<Word>>
}