package com.nikitinsky.interactive.learning.app.presentation.screens.levelcontent

import android.util.Log
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
                val symbols = (currentLevel.kanaList + currentLevel.kanaList + currentLevel.kanaList)
                    .map { it.japaneseSymbol }
                    .shuffled()

                previousState.copy(
                    kanaList = currentLevel.kanaList,
                    showedKana = currentLevel.kanaList.firstOrNull(),
                    miniGameList = symbols
                )
            }
        }
    }

    fun processCommand(command: LevelContentCommand) {
        when(command) {
            LevelContentCommand.ShowNextKana -> {
                _state.update { previousState ->
                    val showedKanaIndex = previousState.kanaList.indexOf(previousState.showedKana)
                    previousState.copy(
                        showedKana = previousState.kanaList[showedKanaIndex + 1],
                        miniGameList = updatedMiniGameList(),
                        answeredKana = mapOf()
                    )
                }
            }
            LevelContentCommand.ShowPreviousKana -> {
                _state.update { previousState ->
                    val showedKanaIndex = previousState.kanaList.indexOf(previousState.showedKana)
                    previousState.copy(
                        showedKana = previousState.kanaList[showedKanaIndex - 1],
                        miniGameList = updatedMiniGameList(),
                        answeredKana = mapOf()
                    )
                }
            }

            is LevelContentCommand.CheckKana -> {
                _state.update { previousState ->
                    val isCorrect = command.kanaJpSymbol == previousState.showedKana?.japaneseSymbol
                    val newAnswers = previousState.answeredKana.toMutableMap()
                    newAnswers[command.index] = isCorrect
                    previousState.copy(
                        answeredKana = newAnswers
                    )
                }
            }
        }
    }

    fun updatedMiniGameList(): List<String> {
        val list = _state.value.kanaList
        return (list + list + list)
            .map { it.japaneseSymbol }
            .shuffled()
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

    data class CheckKana(val index: Int, val kanaJpSymbol: String): LevelContentCommand
}

data class LevelContentState(
    val kanaList: List<Kana> = listOf(),
    val showedKana: Kana? = null,
    val selectedGameKana: String = "",
    val answeredKana: Map<Int, Boolean> = mapOf(),
    val miniGameList: List<String> = listOf()
)