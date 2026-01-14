package com.nikitinsky.interactive.learning.app.domain.repository

import com.nikitinsky.interactive.learning.app.domain.model.MainMenuItem
import kotlinx.coroutines.flow.Flow

interface MenuRepository {

    fun getAllMainMenuItems(): Flow<List<MainMenuItem>>
}