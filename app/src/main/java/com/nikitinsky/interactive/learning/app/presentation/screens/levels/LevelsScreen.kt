package com.nikitinsky.interactive.learning.app.presentation.screens.levels

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nikitinsky.interactive.learning.app.R
import com.nikitinsky.interactive.learning.app.domain.entity.Level

@Composable
fun LevelsScreen(
    modifier: Modifier = Modifier,
    viewModel: LevelsViewModel = hiltViewModel(),
    onLevelClick: (Level) -> Unit
) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        val state by viewModel.state.collectAsState()
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    KanaTypeButton(
                        text = stringResource(R.string.hiragana),
                        enabled = state.isHiragana,
                        onClick = {
                            viewModel.processCommand(LevelsCommand.SwitchKanaType)
                        }
                    )
                    KanaTypeButton(
                        text = stringResource(R.string.katakana),
                        enabled = !state.isHiragana,
                        onClick = {
                            viewModel.processCommand(LevelsCommand.SwitchKanaType)
                        }
                    )
                }
            }
            items(
                items = state.levels,
                key = { it.id }
            ) {
                LevelCard(
                    level = it,
                    onLevelClick = onLevelClick
                )
            }
        }
    }
}

@Composable
fun KanaTypeButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = {
            onClick()
        },
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
        )
    }
}

@Composable
fun LevelCard(
    modifier: Modifier = Modifier,
    level: Level,
    onLevelClick: (Level) -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth()
            .clickable {
                onLevelClick(level)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
    ) {
        val title =
            level.kanaList.joinToString(", ") { kana ->
                kana.japaneseSymbol
            }
        val subtitle =
            level.kanaList.joinToString(", ") { kana ->
                kana.romaji
            }
        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                text = subtitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}