package com.nikitinsky.interactive.learning.app.data.repository

import com.nikitinsky.interactive.learning.app.domain.model.MainMenuItem
import com.nikitinsky.interactive.learning.app.domain.repository.MenuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object TestMenuRepository : MenuRepository {
    override fun getAllMainMenuItems(): Flow<List<MainMenuItem>> = flow {
        val mainMenuItems = listOf(
            MainMenuItem.HIRAGANA,
            MainMenuItem.KATAKANA,
            MainMenuItem.KANJI,
            MainMenuItem.SETTINGS,
            MainMenuItem.EXIT,
        )
        emit(mainMenuItems)
    }
}