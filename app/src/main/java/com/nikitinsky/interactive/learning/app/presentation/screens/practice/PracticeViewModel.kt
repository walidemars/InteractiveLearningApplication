package com.nikitinsky.interactive.learning.app.presentation.screens.practice

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitinsky.interactive.learning.app.domain.entity.Kana
import com.nikitinsky.interactive.learning.app.domain.entity.Word
import com.nikitinsky.interactive.learning.app.domain.usecase.GetKanaForLevelUseCase
import com.nikitinsky.interactive.learning.app.presentation.screens.practice.PracticeState.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = PracticeViewModel.Factory::class)
class PracticeViewModel @AssistedInject constructor(
    getKanaForLevelUseCase: GetKanaForLevelUseCase,
    @Assisted("levelId") private val levelId: Int
): ViewModel() {

    private val _state = MutableStateFlow<PracticeState>(PracticeState.FirstGame())
    val state = _state.asStateFlow()
    private var kanaList: List<Kana> = emptyList()
    private var firstSelection: Pair<Kana, Boolean>? = null

    private val MAX_ROUNDS = 3

    init {
        viewModelScope.launch {
            getKanaForLevelUseCase(levelId).collect { list ->
                kanaList = list
                if (_state.value is PracticeState.FirstGame && (_state.value as PracticeState.FirstGame).leftColumn.isEmpty()) {
                    _state.value = PracticeState.FirstGame(
                        leftColumn = list.map { KanaCardViewModel(kana = it, status = ButtonStatus.NOT_PRESSED) }.shuffled(),
                        rightColumn = list.map { KanaCardViewModel(kana = it, status = ButtonStatus.NOT_PRESSED) }.shuffled()
                    )
                }
            }
        }
    }

    fun processCommand(command: PracticeCommand) {
        when(command) {
            is PracticeCommand.CheckAnswer -> {
                    when(val currentState = _state.value) {
                        is PracticeState.FirstGame -> {
                            if (command.selectKana.status == ButtonStatus.PRESSED || command.selectKana.status == ButtonStatus.CORRECT) return

                            val currentSelection = firstSelection
                            if (currentSelection == null) {
                                updateStatus(
                                    command.selectKana.kana.japaneseSymbol,
                                    command.isLeft,
                                    ButtonStatus.PRESSED
                                )
                                firstSelection = command.selectKana.kana to command.isLeft
                            } else {
                                val (firstKana, firstIsLeft) = currentSelection
                                if (firstIsLeft == command.isLeft) {
                                    updateStatus(
                                        firstKana.japaneseSymbol,
                                        firstIsLeft,
                                        ButtonStatus.NOT_PRESSED
                                    )
                                    updateStatus(
                                        command.selectKana.kana.japaneseSymbol,
                                        command.isLeft,
                                        ButtonStatus.PRESSED
                                    )
                                    firstSelection = command.selectKana.kana to command.isLeft
                                } else {
                                    checkMatch(firstKana, command.selectKana.kana, firstIsLeft)
                                    firstSelection = null
                                }
                            }
                        }
                        is PracticeState.SecondGame -> {

                        }
                        is PracticeState.FourthGame -> {

                        }
                        is PracticeState.ThirdGame -> {

                    }
                }
            }
            PracticeCommand.StartFourthGame -> {
                _state.update {
                    PracticeState.FourthGame(
                        kanaList = kanaList,
                        word = null
                    )
                }
            }
            PracticeCommand.StartSecondGame -> {
                _state.update {
                    SecondGame(
                        kanaList = kanaList,
                        targetKana = kanaList.randomOrNull()
                    )
                }
            }
            PracticeCommand.StartThirdGame -> {
                _state.update {
                    ThirdGame(
                        kanaList = kanaList,
                        word = null
                    )
                }
            }

        }
    }

    private fun checkMatch(firstKana: Kana, secondKana: Kana, firstIsLeft: Boolean) {
        if (firstKana.japaneseSymbol == secondKana.japaneseSymbol) {
            updateStatus(firstKana.japaneseSymbol, firstIsLeft, ButtonStatus.CORRECT)
            updateStatus(secondKana.japaneseSymbol, !firstIsLeft, ButtonStatus.CORRECT)
            handlePairMatched()
        } else {
            viewModelScope.launch {
                updateStatus(firstKana.japaneseSymbol, firstIsLeft, ButtonStatus.INCORRECT)
                updateStatus(secondKana.japaneseSymbol, !firstIsLeft, ButtonStatus.INCORRECT)
                delay(1000)
                updateStatus(firstKana.japaneseSymbol, firstIsLeft, ButtonStatus.NOT_PRESSED)
                updateStatus(secondKana.japaneseSymbol, !firstIsLeft, ButtonStatus.NOT_PRESSED)
            }
        }
    }

    private fun handlePairMatched() {
        _state.update { currentState ->
            if (currentState is PracticeState.FirstGame) {
                val newMatchedCount = currentState.matchedPairs + 1

                if (newMatchedCount == currentState.leftColumn.size) {
                    if (currentState.roundCount < MAX_ROUNDS) {
                        generateNewRound(currentState.roundCount + 1)
                    } else {
                        PracticeState.SecondGame()
                    }
                } else {
                    currentState.copy(matchedPairs = newMatchedCount)
                }
            } else currentState
        }
    }

    private fun generateNewRound(nextRoundNumber: Int): PracticeState.FirstGame {
        return PracticeState.FirstGame(
            leftColumn = kanaList.map { KanaCardViewModel(kana = it, status = ButtonStatus.NOT_PRESSED) }.shuffled(),
            rightColumn = kanaList.map { KanaCardViewModel(kana = it, status = ButtonStatus.NOT_PRESSED) }.shuffled(),
            roundCount = nextRoundNumber,
            matchedPairs = 0
        )
    }

    private fun updateStatus(kanaString: String, isLeft: Boolean, status: ButtonStatus) {
        _state.update { currentState ->
            if (currentState is PracticeState.FirstGame) {
                if (isLeft) {
                    val newLeftColumn = currentState.leftColumn.map {
                        if (it.kana.japaneseSymbol == kanaString) {
                            it.copy(status = status)
                        } else it
                    }
                    currentState.copy(leftColumn = newLeftColumn)
                } else {
                    val newRightColumn = currentState.rightColumn.map {
                        if (it.kana.japaneseSymbol == kanaString) {
                            it.copy(status = status)
                        } else it
                    }
                    currentState.copy(rightColumn = newRightColumn)
                }
            } else {
                currentState
            }
       }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("levelId") levelId: Int
        ): PracticeViewModel
    }
}

data class KanaCardViewModel(
    val kana: Kana,
    val status: ButtonStatus = ButtonStatus.NOT_PRESSED
)

sealed interface PracticeCommand {

    data class CheckAnswer(val selectKana: KanaCardViewModel, val isLeft: Boolean): PracticeCommand

    data object StartSecondGame: PracticeCommand

    data object StartThirdGame: PracticeCommand

    data object StartFourthGame: PracticeCommand
}

sealed interface PracticeState {

    data class FirstGame(
        val leftColumn: List<KanaCardViewModel> = listOf(),
        val rightColumn: List<KanaCardViewModel> = listOf(),
        val roundCount: Int = 1,
        val matchedPairs: Int = 0,
    ): PracticeState

    data class SecondGame(
        val kanaList: List<Kana> = listOf(),
        val targetKana: Kana? = null
    ): PracticeState

    data class ThirdGame(
        val kanaList: List<Kana> = listOf(),
        val word: Word? = null
    ): PracticeState

    data class FourthGame(
        val kanaList: List<Kana> = listOf(),
        val word: Word? = null
    ): PracticeState
}

enum class ButtonStatus {
    NOT_PRESSED,
    PRESSED,
    CORRECT,
    INCORRECT
}