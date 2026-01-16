package com.nikitinsky.interactive.learning.app.presentation.screens.levels_menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitinsky.interactive.learning.app.domain.model.KanaType
import com.nikitinsky.interactive.learning.app.domain.model.Level
import com.nikitinsky.interactive.learning.app.domain.usecase.GetAllLevelsByTypeUseCase
import com.nikitinsky.interactive.learning.app.domain.usecase.GetLevelUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

@HiltViewModel(assistedFactory = LevelsMenuViewModel.Factory::class)
class LevelsMenuViewModel @AssistedInject constructor (
    private val getAllLevelsByTypeUseCase: GetAllLevelsByTypeUseCase,
    @Assisted("kanaType") private val kanaType: KanaType
): ViewModel() {

    private val _state = MutableStateFlow<LevelsMenuScreenState>(LevelsMenuScreenState.ChoosingLevel())
    val state = _state.asStateFlow()

    init {
        getAllLevelsByTypeUseCase(kanaType)
            .onEach { levels ->
                _state.update {
                    LevelsMenuScreenState.ChoosingLevel(levels)
                }
            }.launchIn(viewModelScope)
    }

    fun processCommand(command: LevelsMenuCommand) {
        when(command) {
            LevelsMenuCommand.Back -> {
                _state.update { LevelsMenuScreenState.ReturnToMainMenu }
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("kanaType") kanaType: KanaType
        ): LevelsMenuViewModel
    }
}

sealed interface LevelsMenuCommand {
    data object Back: LevelsMenuCommand
}

sealed interface LevelsMenuScreenState {
    data class ChoosingLevel (
        val levelsList: List<Level> = listOf()
    ): LevelsMenuScreenState

    data object ReturnToMainMenu: LevelsMenuScreenState
}



