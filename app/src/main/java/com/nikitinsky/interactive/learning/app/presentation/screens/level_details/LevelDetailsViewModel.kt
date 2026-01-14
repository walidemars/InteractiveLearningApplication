package com.nikitinsky.interactive.learning.app.presentation.screens.level_details

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LevelDetailsViewModel @Inject constructor(

) : ViewModel() {

    private val _currentMode = mutableStateOf<StudyMode>(StudyMode.Selection)
    val currentMode: State<StudyMode> = _currentMode

    fun setMode(mode: StudyMode) {
        _currentMode.value = mode
    }

    fun resetToSelection() {
        _currentMode.value = StudyMode.Selection
    }
}

sealed class StudyMode {
    object Selection: StudyMode()
    object Flashcards: StudyMode()
    object Test: StudyMode()
    object GameKana: StudyMode()
    object GameWords: StudyMode()
}