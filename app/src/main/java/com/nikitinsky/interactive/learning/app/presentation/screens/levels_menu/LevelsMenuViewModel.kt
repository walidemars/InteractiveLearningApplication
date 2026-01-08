package com.nikitinsky.interactive.learning.app.presentation.screens.levels_menu

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nikitinsky.interactive.learning.app.domain.model.Level

class LevelsMenuViewModel(context: Context): ViewModel() {

    private val _levelsState = mutableStateOf<List<Level>>(emptyList())
    val levelsState: State<List<Level>> = _levelsState

    private var currentType: String = "" // тип алфавита Хирагана\Катакана

    fun loadLevels(type: String) {
        currentType = type

        _levelsState.value = listOf(
            Level(id = 1, title = "Level 1", japaneseChars = "a i u e o", isUnlocked = true),
            Level(id = 2, title = "Level 2", japaneseChars = "ka ki ku ke ko"),
            Level(id = 3, title = "Level 3", japaneseChars = "sa si su se so"),
        )
    }
}


