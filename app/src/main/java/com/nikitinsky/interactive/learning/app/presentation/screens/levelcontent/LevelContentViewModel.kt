package com.nikitinsky.interactive.learning.app.presentation.screens.levelcontent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitinsky.interactive.learning.app.domain.entity.Kana
import com.nikitinsky.interactive.learning.app.domain.usecase.GetLevelUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = LevelContentViewModel.Factory::class)
class LevelContentViewModel @AssistedInject constructor(
    private val getLevelUseCase: GetLevelUseCase,
    @Assisted("levelId") private val levelId: Int
): ViewModel() {

    private val _state = MutableStateFlow(LevelContentState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val currentLevel = getLevelUseCase(levelId)
            _state.update { previousState ->
                previousState.copy(
                    kanaList = currentLevel.kanaList,
                    showedKana = currentLevel.kanaList.firstOrNull()
                )
            }
        }
    }

    fun processCommand(command: LevelContentCommand) {
        when(command) {
            LevelContentCommand.ShowNextKana -> {
                _state.update { previousState ->
                    val showedKanaIndex = previousState.kanaList.indexOf(previousState.showedKana)
                    previousState.copy(showedKana = previousState.kanaList[showedKanaIndex + 1])
                }
            }
            LevelContentCommand.ShowPreviousKana -> {
                _state.update { previousState ->
                    val showedKanaIndex = previousState.kanaList.indexOf(previousState.showedKana)
                    previousState.copy(showedKana = previousState.kanaList[showedKanaIndex - 1])
                }
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("levelId") levelId: Int
        ): LevelContentViewModel
    }
}

sealed interface LevelContentCommand {

    data object ShowNextKana: LevelContentCommand

    data object ShowPreviousKana: LevelContentCommand
}

data class LevelContentState(
    val kanaList: List<Kana> = listOf(),
    val showedKana: Kana? = null
)