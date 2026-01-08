package com.nikitinsky.interactive.learning.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nikitinsky.interactive.learning.app.presentation.screens.main_menu.MainMenuScreen

@Composable
fun NavGraph(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MainMenu.route
    ) {
        composable(Screen.MainMenu.route) {
            MainMenuScreen(
                onNavigateToHiragana = {

                },
                onNavigateToKatakana = {

                },
                onNavigateToKanji = {

                },
                onNavigateToSettings = {

                },
                onNavigateToExit = {

                }
            )
        }
        composable(Screen.HiraganaLevels.route) {

        }
        composable(Screen.Level.route) {

        }
        composable(Screen.Settings.route) {

        }
    }
}

sealed class Screen(val route: String) {

    data object MainMenu: Screen("main_menu")

    data object HiraganaLevels: Screen("hiragana_levels")

    data object KatakanaLevels: Screen("katakana_levels")

    data object Level: Screen("level")
    data object Settings: Screen("settings")
}