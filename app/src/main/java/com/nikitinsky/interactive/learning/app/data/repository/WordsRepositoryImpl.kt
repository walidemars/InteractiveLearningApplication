package com.nikitinsky.interactive.learning.app.data.repository

import com.nikitinsky.interactive.learning.app.data.local.dao.WordDao
import com.nikitinsky.interactive.learning.app.data.mapper.toEntities
import com.nikitinsky.interactive.learning.app.data.mapper.toEntity
import com.nikitinsky.interactive.learning.app.domain.model.Word
import com.nikitinsky.interactive.learning.app.domain.repository.WordsRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WordsRepositoryImpl @Inject constructor(
    private val wordDao: WordDao
) : WordsRepository {
    override fun getWordById(wordId: Int): Word {
        return wordDao.getWordById(wordId).toEntity()
    }

    override fun getWordsForLevel(levelId: Int): Flow<List<Word>> {
        return wordDao.getWordsForLevel(levelId).map { it.toEntities() }
    }
}