@file:OptIn(ExperimentalMaterial3Api::class)

package com.nikitinsky.interactive.learning.app.presentation.screens.levelcontent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun LevelContentScreen(
    modifier: Modifier = Modifier,
    levelId: Int,
    onBackClick: () -> Unit,
    viewModel: LevelContentViewModel = hiltViewModel(
        creationCallback = { factory: LevelContentViewModel.Factory ->
            factory.create(levelId)
        }
    )
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Level ${levelId % 100}")
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
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        val state by viewModel.state.collectAsState()
        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    state.showedKana?.let {
                        Text(
                            modifier = Modifier,
                            text = it.japaneseSymbol,
                            fontSize = 84.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    state.showedKana?.let {
                        Text(
                            modifier = Modifier,
                            text = it.romaji,
                            fontSize = 84.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircleButton(
                        onClick = { viewModel.processCommand(LevelContentCommand.ShowNextKana) },
                        enabled = state.showedKana != state.kanaList.lastOrNull(),
                        text = "Next kana"
                    )
                    CircleButton(
                        onClick = { viewModel.processCommand(LevelContentCommand.ShowPreviousKana) },
                        enabled = state.showedKana != state.kanaList.firstOrNull(),
                        text = "Previous kana"
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = "Find romaji [${state.showedKana?.romaji}]",
                    fontSize = 20.sp
                )
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    maxItemsInEachRow = 5
                ) {
                    state.miniGameList.forEachIndexed { index, kana ->

                        val isCorrectAnswer = state.answeredKana[index]
                        Button(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(64.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = when(isCorrectAnswer) {
                                    true -> {
                                        MaterialTheme.colorScheme.secondary
                                    }
                                    false -> {
                                        MaterialTheme.colorScheme.tertiary
                                    }
                                    null -> {
                                        MaterialTheme.colorScheme.surfaceVariant
                                    }
                                },
                                contentColor = when(isCorrectAnswer) {
                                    true -> {
                                        MaterialTheme.colorScheme.surface
                                    }
                                    false -> {
                                        MaterialTheme.colorScheme.surface
                                    }
                                    null -> {
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                    }
                                }
                            ),
                            onClick = {
                                viewModel.processCommand(LevelContentCommand.CheckKana(index,kana))
                            }
                        ) {
                            Text(
                                text = kana,
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
            Button(
                modifier = Modifier
                    .padding(16.dp),
                onClick = {

                },
            ) {
                Text(text = "Go to practice")
            }
        }
    }
}

@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean,
    text: String
) {
    Button(
        modifier = Modifier
            .size(120.dp),
        onClick = {
            onClick()
        },
        enabled = enabled,
        shape = CircleShape,
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}