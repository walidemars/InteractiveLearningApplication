package com.nikitinsky.interactive.learning.app.presentation.screens.levels

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nikitinsky.interactive.learning.app.R
import com.nikitinsky.interactive.learning.app.domain.entity.Level
import com.nikitinsky.interactive.learning.app.presentation.ui.theme.RedJapan
import java.nio.file.WatchEvent

@Composable
fun LevelsScreen(
    modifier: Modifier = Modifier,
    viewModel: LevelsViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = modifier
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
                    val disabledButtonColors = ButtonDefaults.buttonColors()
                    Button(
                        onClick = {
                            viewModel.processCommand(LevelsCommand.SwitchKanaType)
                        },
                        enabled = state.isHiragana
                    ) {
                        Text(text = stringResource(R.string.hiragana))
                    }
                    Button(
                        onClick = {
                            viewModel.processCommand(LevelsCommand.SwitchKanaType)
                        },
                        enabled = !state.isHiragana
                    ) {
                        Text(text = stringResource(R.string.katakana))
                    }
                }
            }
            items(
                items = state.levels,
                key = { it.id }
            ) {
                LevelCard(
                    level = it
                )
            }
        }
    }
}

@Composable
fun LevelCard(
    modifier: Modifier = Modifier,
    level: Level
) {
    Card(
        modifier = modifier.fillMaxWidth(),
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
                fontSize = 20.sp
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                text = subtitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp
            )
        }
    }
}