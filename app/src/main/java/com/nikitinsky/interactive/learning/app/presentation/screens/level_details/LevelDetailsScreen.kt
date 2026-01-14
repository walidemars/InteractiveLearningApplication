package com.nikitinsky.interactive.learning.app.presentation.screens.level_details

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LevelDetailsScreen(
    levelId: String?,
    onBackClick: () -> Unit,
    viewModel: LevelDetailsViewModel = viewModel()
) {
}


