package com.nikitinsky.interactive.learning.app.presentation.screens.main_menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitinsky.interactive.learning.app.domain.model.MainMenuItem
import com.nikitinsky.interactive.learning.app.domain.usecase.main_menu.GetAllMainMenuItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor (
    private val getAllMainMenuItemsUseCase: GetAllMainMenuItemsUseCase
): ViewModel() {

    private val _state = MutableStateFlow(MainMenuScreenState())
    val state = _state.asStateFlow()

    init {
        loadMenuItems()
    }

    private fun loadMenuItems() {
        getAllMainMenuItemsUseCase()
            .onEach { menuItems ->
                _state.update { currentState ->
                    currentState.copy(mainMenuItems = menuItems)
                }
            }
            .launchIn(viewModelScope)
    }

    fun processCommand(command: MainMenuCommand) {
        viewModelScope.launch {
            when(command) {
                is MainMenuCommand.NavigateToExit -> {

                }
                is MainMenuCommand.NavigateToHiragana -> {

                }
                is MainMenuCommand.NavigateToKanji -> {

                }
                is MainMenuCommand.NavigateToKatakana -> {

                }
                is MainMenuCommand.NavigateToSettings -> {

                }
            }
        }
    }
}

sealed interface MainMenuCommand {
    data class NavigateToHiragana(val mainMenuItem: MainMenuItem): MainMenuCommand

    data class NavigateToKatakana(val mainMenuItem: MainMenuItem): MainMenuCommand

    data class NavigateToKanji(val mainMenuItem: MainMenuItem): MainMenuCommand

    data class NavigateToSettings(val mainMenuItem: MainMenuItem): MainMenuCommand

    data class NavigateToExit(val mainMenuItem: MainMenuItem): MainMenuCommand
}

data class MainMenuScreenState(
    val mainMenuItems: List<MainMenuItem> = listOf()
)


