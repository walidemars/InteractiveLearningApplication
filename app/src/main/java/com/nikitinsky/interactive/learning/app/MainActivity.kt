package com.nikitinsky.interactive.learning.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nikitinsky.interactive.learning.app.presentation.screens.levels_menu.LevelsMenuScreen
import com.nikitinsky.interactive.learning.app.presentation.theme.InteractiveLearningApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InteractiveLearningApplicationTheme {
//                MainMenuScreen(
//                    onNavigateToHiragana = {},
//                    onNavigateToKatakana = {},
//                    onNavigateToKanji = {},
//                    onNavigateToSettings = {},
//                    onNavigateToExit = {}
//                )
                LevelsMenuScreen(
                    type = "Hiragana",
                    onLevelClick = {}
                ) { }
            }
        }
    }
}