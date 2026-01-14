package com.nikitinsky.interactive.learning.app.data.repository

import com.nikitinsky.interactive.learning.app.domain.model.KanaSymbol
import com.nikitinsky.interactive.learning.app.domain.model.KanaType
import com.nikitinsky.interactive.learning.app.domain.model.Level
import com.nikitinsky.interactive.learning.app.domain.model.Word
import com.nikitinsky.interactive.learning.app.domain.repository.KanaRepository
import com.nikitinsky.interactive.learning.app.domain.repository.WordsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

object TestWordRepositoryImpl : WordsRepository {
    private val wordListFlow = MutableStateFlow<List<Word>>(listOf())
    private val levelListFlow = MutableStateFlow<List<Level>>(listOf())

    override fun getWordById(wordId: Int): Word {
        return wordListFlow.value.first { it.id == wordId }
    }

    override fun getWordsForLevel(levelId: Int): Flow<List<Word>> {
        val level = levelListFlow.value.first { it.id == levelId }
        return wordListFlow.map { wordList ->
            wordList.filter {
                level.wordIds.contains(it.id)
            }
        }
    }
}