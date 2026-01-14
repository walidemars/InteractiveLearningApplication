package com.nikitinsky.interactive.learning.app.domain.usecase.main_menu

import com.nikitinsky.interactive.learning.app.domain.model.MainMenuItem
import com.nikitinsky.interactive.learning.app.domain.repository.MenuRepository
import kotlinx.coroutines.flow.Flow

class GetAllMainMenuItemsUseCase(
    private val repository: MenuRepository
) {
    operator fun invoke(): Flow<List<MainMenuItem>> {
        return repository.getAllMainMenuItems()
    }
}