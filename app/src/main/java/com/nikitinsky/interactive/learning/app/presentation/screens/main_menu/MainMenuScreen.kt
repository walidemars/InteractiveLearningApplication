package com.nikitinsky.interactive.learning.app.presentation.screens.main_menu

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nikitinsky.interactive.learning.app.presentation.ui.theme.RedJapan

@Composable
fun MainMenuScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current.applicationContext,
    viewModel: MainMenuViewModel = viewModel {
        MainMenuViewModel(context)
    },
    onNavigateToHiragana: () -> Unit,
    onNavigateToKatakana: () -> Unit,
    onNavigateToKanji: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToExit: () -> Unit,
) {

    val state = viewModel.state.collectAsState()

    val currentState = state.value

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = modifier,
                text = "Nihongo Learn",
                fontSize = 24.sp,
                color = RedJapan,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

        }
    }
}

