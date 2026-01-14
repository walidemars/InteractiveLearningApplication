package com.nikitinsky.interactive.learning.app.presentation.screens.levels_menu

import androidx.lifecycle.ViewModel
import com.nikitinsky.interactive.learning.app.domain.usecase.GetAllLevelsByTypeUseCase
import com.nikitinsky.interactive.learning.app.domain.usecase.GetLevelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LevelsMenuViewModel @Inject constructor (
    private val getAllLevelsByTypeUseCase: GetAllLevelsByTypeUseCase,
    private val getLevelUseCase: GetLevelUseCase
): ViewModel() {

    fun loadLevels(type: String) {



    }
}


