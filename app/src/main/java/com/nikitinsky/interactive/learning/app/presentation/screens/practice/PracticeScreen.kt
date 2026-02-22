@file:OptIn(ExperimentalMaterial3Api::class)

package com.nikitinsky.interactive.learning.app.presentation.screens.practice

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun PracticeScreen(
    modifier: Modifier = Modifier,
    levelId: Int,
    onBackClick: () -> Unit,
    viewModel: PracticeViewModel = hiltViewModel(
        creationCallback = { factory: PracticeViewModel.Factory ->
            factory.create(levelId)
        }
    )
) {
    val state = viewModel.state.collectAsState()
    when(val currentState = state.value) {
        is PracticeState.FirstGame -> {
            Scaffold(
                modifier = modifier,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Practice 1")
                        },
                        navigationIcon = {
                            Icon(
                                modifier = Modifier
                                    .padding(start = 16.dp, end = 16.dp)
                                    .clickable {
                                        onBackClick()
                                    },
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    )
                }
            ) { innerPadding ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                            .padding(horizontal = 16.dp, vertical = 16.dp)
                    ) {
                        currentState.leftColumn.forEach { kanaCard ->
                            KanaCard(
                                kanaCardViewModel = kanaCard,
                                text = kanaCard.kana.japaneseSymbol
                            ) {
                                viewModel.processCommand(PracticeCommand.CheckAnswer(kanaCard, true))
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.weight(1f)
                            .padding(horizontal = 16.dp, vertical = 16.dp)
                    ) {
                        currentState.rightColumn.forEach { kanaCard ->
                            KanaCard(
                                kanaCardViewModel = kanaCard,
                                text = kanaCard.kana.romaji
                            ) {
                                viewModel.processCommand(PracticeCommand.CheckAnswer(kanaCard, false))
                            }
                        }
                    }
                }
            }
        }
        is PracticeState.FourthGame -> {

        }
        is PracticeState.SecondGame -> {

        }
        is PracticeState.ThirdGame -> {

        }
    }
}

@Composable
fun KanaCard(
    modifier: Modifier = Modifier,
    kanaCardViewModel: KanaCardViewModel,
    text: String,
    onClick: () -> Unit
) {
    val backgroundColor = when(kanaCardViewModel.status) {
        ButtonStatus.NOT_PRESSED -> {
            MaterialTheme.colorScheme.primary
        }
        ButtonStatus.PRESSED -> {
            MaterialTheme.colorScheme.surface
        }
        ButtonStatus.CORRECT -> {
            MaterialTheme.colorScheme.secondary
        }
        ButtonStatus.INCORRECT -> {
            MaterialTheme.colorScheme.tertiary
        }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(60.dp)
            .clickable(enabled = kanaCardViewModel.status != ButtonStatus.CORRECT) {
                onClick()
            },
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, fontSize = 20.sp)
        }
    }
}