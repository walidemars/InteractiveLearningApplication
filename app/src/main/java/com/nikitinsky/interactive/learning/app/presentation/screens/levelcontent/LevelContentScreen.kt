@file:OptIn(ExperimentalMaterial3Api::class)

package com.nikitinsky.interactive.learning.app.presentation.screens.levelcontent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
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
                    Text(text = "Level $levelId")
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
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier//.weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                        //.fillMaxWidth()
                        .padding(end = 16.dp),
                    verticalArrangement = Arrangement.Center,
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
                        //.fillMaxWidth()

                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.End
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {

                        }
                    ) {
                        Text(text = "Memorizing kana")
                    }
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            viewModel.processCommand(LevelContentCommand.ShowNextKana)
                        },
                        enabled = state.showedKana != state.kanaList.lastOrNull()
                    ) {
                        Text(text = "Next kana")
                    }
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            viewModel.processCommand(LevelContentCommand.ShowPreviousKana)
                        },
                        enabled = state.showedKana != state.kanaList.firstOrNull()
                    ) {
                        Text(text = "Previous kana")
                    }
                }
            }
            Button(
                modifier = Modifier//.weight(1f)
                    .padding(16.dp),
                onClick = {

                }
            ) {
                Text(text = "Go to practice")
            }
        }

    }
}