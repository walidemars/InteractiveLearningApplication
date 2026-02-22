package com.nikitinsky.interactive.learning.app.presentation.screens.levelcontent

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitinsky.interactive.learning.app.domain.entity.Kana
import com.nikitinsky.interactive.learning.app.domain.entity.Level
import com.nikitinsky.interactive.learning.app.domain.usecase.GetKanaForLevelUseCase
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

@HiltViewModel(assistedFactory = LevelContentViewModel.Factory::class)
class LevelContentViewModel @AssistedInject constructor(
    getKanaForLevelUseCase: GetKanaForLevelUseCase,
    getLevelUseCase: GetLevelUseCase,
    @Assisted("levelId") private val levelId: Int
): ViewModel() {

    private val _state = MutableStateFlow(LevelContentState())
    val state = _state.asStateFlow()

    init {
        getKanaForLevelUseCase(levelId)
            .onEach { list ->
                _state.update { previousState ->
                    previousState.copy(
                        kanaList = list,
                        showedKana = list.firstOrNull(),
                        miniGameList = (list + list + list)
                            .map { it.japaneseSymbol }
                            .shuffled(),
                        currentLevel = getLevelUseCase(levelId)
                    )
                }
                Log.d("LevelContentViewModel", "levelId = ${_state.value.currentLevel}")
            }
            .launchIn(viewModelScope)
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
    val answeredKana: Map<Int, Boolean> = mapOf(),
    val miniGameList: List<String> = listOf(),
    val currentLevel: Level? = null
)