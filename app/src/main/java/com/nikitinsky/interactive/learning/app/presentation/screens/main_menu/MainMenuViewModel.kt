package com.nikitinsky.interactive.learning.app.presentation.screens.main_menu

import androidx.lifecycle.ViewModel
import com.nikitinsky.interactive.learning.app.domain.usecase.main_menu.GetAllMainMenuItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor (
    private val getAllMainMenuItemsUseCase: GetAllMainMenuItemsUseCase
): ViewModel() {


}
