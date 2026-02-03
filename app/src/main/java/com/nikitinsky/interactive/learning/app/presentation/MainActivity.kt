package com.nikitinsky.interactive.learning.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nikitinsky.interactive.learning.app.presentation.screens.levels.LevelsScreen
import com.nikitinsky.interactive.learning.app.presentation.ui.theme.InteractiveLearningApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InteractiveLearningApplicationTheme {
                LevelsScreen()
            }
        }
    }
}