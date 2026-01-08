package com.nikitinsky.interactive.learning.app.presentation.screens.main_menu

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nikitinsky.interactive.learning.app.presentation.theme.Gray300
import com.nikitinsky.interactive.learning.app.presentation.theme.RedJapan
import com.nikitinsky.interactive.learning.app.presentation.theme.Gray100

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
            Spacer(modifier = Modifier.height(48.dp))

            MenuButton(
                text = "Hiragana",
                onClick = onNavigateToHiragana
            )
            Spacer(modifier = Modifier.height(24.dp))

            MenuButton(
                text = "Katakana",
                onClick = onNavigateToKatakana
            )
            Spacer(modifier = Modifier.height(24.dp))

            MenuButton(
                text = "Kanji",
                onClick = onNavigateToKanji
            )
            Spacer(modifier = Modifier.height(24.dp))

            MenuButton(
                text = "Settings",
                onClick = onNavigateToSettings
            )
            Spacer(modifier = Modifier.height(24.dp))

            MenuButton(
                text = "Exit",
                onClick = onNavigateToExit
            )
        }
    }
}

@Composable
fun MenuButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            containerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
        onClick = onClick,
        enabled = enabled
    ) {
        Text(
            text = text,
            fontSize = 20.sp
        )
    }
}