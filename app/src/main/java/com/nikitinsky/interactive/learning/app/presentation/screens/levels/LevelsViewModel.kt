@file:OptIn(ExperimentalCoroutinesApi::class)

package com.nikitinsky.interactive.learning.app.presentation.screens.levels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitinsky.interactive.learning.app.domain.entity.KanaType
import com.nikitinsky.interactive.learning.app.domain.entity.Level
import com.nikitinsky.interactive.learning.app.domain.usecase.GetLevelUseCase
import com.nikitinsky.interactive.learning.app.domain.usecase.GetLevelsByKanaTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LevelsViewModel @Inject constructor(
    private val getLevelsByKanaTypeUseCase: GetLevelsByKanaTypeUseCase,
    private val getLevelUseCase: GetLevelUseCase
): ViewModel() {

    private val _state = MutableStateFlow(LevelsState())
    val state = _state.asStateFlow()

    init {
        _state.map { it.kanaType }
            .distinctUntilChanged()
            .flatMapLatest {
                getLevelsByKanaTypeUseCase(it)
            }
            .onEach {
                _state.update { previousState ->
                    previousState.copy(levels = it)
                }
            }
            .launchIn(viewModelScope)
    }

    fun processCommand(command: LevelsCommand) {
        when(command) {
            is LevelsCommand.ClickLevel -> {
                //getLevelUseCase()
            }
            is LevelsCommand.SwitchKanaType -> {
                _state.update { previousState ->
                    val currentKanaType = previousState.kanaType
                    if (currentKanaType == KanaType.HIRAGANA) {
                        previousState.copy(kanaType = KanaType.KATAKANA, isHiragana = true)
                    } else {
                        previousState.copy(kanaType = KanaType.HIRAGANA, isHiragana = false)
                    }
                }
            }
        }
    }
}

sealed interface LevelsCommand {

    data class ClickLevel(val levelId: Int): LevelsCommand

    data object SwitchKanaType: LevelsCommand
}

data class LevelsState(
    val levels: List<Level> = listOf(),
    val kanaType: KanaType = KanaType.HIRAGANA,
    val isHiragana: Boolean = false
)