package com.nikitinsky.interactive.learning.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nikitinsky.interactive.learning.app.domain.model.KanaType
import com.nikitinsky.interactive.learning.app.presentation.screens.main_menu.MainMenuScreen

@Composable
fun NavGraph(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MainMenu.route
    ) {
        composable(
            Screen.MainMenu.route
        ) {
            MainMenuScreen(
                onNavigateToHiraganaClick = {
                    navController.navigate(Screen.LevelsMenu.createRoute(KanaType.HIRAGANA))
                },
                onNavigateToKatakanaClick = {
                    navController.navigate(Screen.LevelsMenu.createRoute(KanaType.KATAKANA))
                },
                onNavigateToKanjiClick = {
                    navController.navigate(Screen.KanjiMenu.route)
                },
                onNavigateToSettingsClick = {
                    navController.navigate(Screen.Settings.route)
                },
                onNavigateToExitClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

sealed class Screen(val route: String) {

    data object MainMenu: Screen("main_menu")

    data object LevelsMenu: Screen("levels_menu/{type}") {

        fun createRoute(type: KanaType) = "levels_menu/$type"
    }

    data object KanjiMenu: Screen("kanji_menu")

    data object LevelDetails: Screen("level_details/{levelId}") {

        fun createRoute(levelId: String) = "level_details/$levelId"
    }
    data object Settings: Screen("settings")
}