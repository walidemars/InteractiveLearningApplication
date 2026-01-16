package com.nikitinsky.interactive.learning.app.presentation.screens.levels_menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nikitinsky.interactive.learning.app.domain.model.KanaType
import com.nikitinsky.interactive.learning.app.domain.model.Level

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LevelsMenuScreen(
    modifier: Modifier = Modifier,
    kanaType: KanaType,
    viewModel: LevelsMenuViewModel = hiltViewModel(
        creationCallback = { factory: LevelsMenuViewModel.Factory ->
            factory.create(kanaType)
        }
    ),
    onLevelClick: (Level) -> Unit,
    onBackClick: () -> Unit
) {
    val state = viewModel.state.collectAsState()
    when(val currentState = state.value) {
        is LevelsMenuScreenState.ChoosingLevel -> {
            Scaffold(
                modifier = Modifier,
            ) { innerPadding ->
                Column(
                    modifier = modifier.padding(innerPadding)
                ) {
                    Title(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        text = kanaType.name
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(128.dp)
                    ) {
                        itemsIndexed(
                            items = currentState.levelsList,
                            key = { _, level -> level.id }
                        ) {
                                index, level ->
                            LevelButton(
                                modifier = Modifier.widthIn(max = 160.dp),
                                text = "Level ${index + 1}",
                                level = level,
                                onLevelButtonClick = onLevelClick
                            )
                        }
                    }
                }


            }
        }
        LevelsMenuScreenState.ReturnToMainMenu -> {
            LaunchedEffect(key1 = Unit) {
                onBackClick()
            }
        }
    }


}

@Composable
private fun Title(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
private fun LevelButton(
    modifier: Modifier = Modifier,
    level: Level,
    text: String,
    onLevelButtonClick: (Level) -> Unit
) {
    Button(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .padding(horizontal = 24.dp, vertical = 16.dp),
        onClick = { onLevelButtonClick(level) },
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Text(
            text = text,
            fontSize = 22.sp,
            maxLines = 1,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}