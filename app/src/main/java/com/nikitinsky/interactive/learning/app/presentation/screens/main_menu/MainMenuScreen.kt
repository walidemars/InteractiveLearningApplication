package com.nikitinsky.interactive.learning.app.presentation.screens.main_menu

import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nikitinsky.interactive.learning.app.domain.model.MainMenuItem
import com.nikitinsky.interactive.learning.app.presentation.ui.theme.RedJapan

@Composable
fun MainMenuScreen(
    modifier: Modifier = Modifier,
    viewModel: MainMenuViewModel = hiltViewModel(),
    onNavigateToHiraganaClick: () -> Unit,
    onNavigateToKatakanaClick: () -> Unit,
    onNavigateToKanjiClick: () -> Unit,
    onNavigateToSettingsClick: () -> Unit,
    onNavigateToExitClick: () -> Unit,
) {

    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 32.dp),
                text = "Nihongo Learn",
                fontSize = 24.sp,
                color = RedJapan,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))

            state.mainMenuItems.forEach {
                MenuItem(
                    modifier = Modifier
                        .weight(1f),
                    title = it.title,
                    onClick = {
                        when(it) {
                            MainMenuItem.HIRAGANA -> onNavigateToHiraganaClick
                            MainMenuItem.KATAKANA -> onNavigateToKatakanaClick
                            MainMenuItem.KANJI -> onNavigateToKanjiClick
                            MainMenuItem.SETTINGS -> onNavigateToSettingsClick
                            MainMenuItem.EXIT -> onNavigateToExitClick
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        onClick = onClick,
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Text(
            text = title,
            fontSize = 22.sp,
            maxLines = 1,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

