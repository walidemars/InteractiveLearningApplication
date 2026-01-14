package com.nikitinsky.interactive.learning.app.presentation.screens.levels_menu

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nikitinsky.interactive.learning.app.data.repository.TestKanaRepositoryImpl
import com.nikitinsky.interactive.learning.app.domain.model.Level
import com.nikitinsky.interactive.learning.app.domain.usecase.GetAllKanaByTypeUseCase
import com.nikitinsky.interactive.learning.app.domain.usecase.GetAllLevelsByTypeUseCase
import com.nikitinsky.interactive.learning.app.domain.usecase.GetKanaByIdUseCase
import com.nikitinsky.interactive.learning.app.domain.usecase.GetKanaForLevelUseCase
import com.nikitinsky.interactive.learning.app.domain.usecase.GetLevelUseCase
import com.nikitinsky.interactive.learning.app.domain.usecase.GetWordByIdUseCase
import com.nikitinsky.interactive.learning.app.domain.usecase.GetWordsForLevelUseCase
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


