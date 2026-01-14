package com.nikitinsky.interactive.learning.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nikitinsky.interactive.learning.app.presentation.navigation.NavGraph
import com.nikitinsky.interactive.learning.app.presentation.ui.theme.InteractiveLearningApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InteractiveLearningApplicationTheme {
                NavGraph()
            }
        }
    }
}