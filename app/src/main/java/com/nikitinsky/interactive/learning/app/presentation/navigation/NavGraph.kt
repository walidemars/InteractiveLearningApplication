package com.nikitinsky.interactive.learning.app.presentation.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nikitinsky.interactive.learning.app.domain.model.KanaType
import com.nikitinsky.interactive.learning.app.presentation.screens.levels_menu.LevelsMenuScreen
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
        composable(
            route = Screen.LevelsMenu.route,
            arguments = listOf(
                navArgument("type") {
                    type = NavType.StringType
                }
            )

        ) { backStackEntry ->
            val kanaType = Screen.LevelsMenu.getKanaType(backStackEntry.arguments)
            LevelsMenuScreen(
                kanaType = kanaType,
                onLevelClick = { level ->
                    navController.navigate(Screen.LevelDetails.createRoute(level.id))
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

sealed class Screen(val route: String) {

    data object MainMenu: Screen("main_menu")

    data object LevelsMenu: Screen("levels_menu/{type}") {

        fun createRoute(type: KanaType) = "levels_menu/${type.name}"

        fun getKanaType(arguments: Bundle?): KanaType {
            val type = arguments?.getString("type") ?: error("KanaType is null")
            return KanaType.valueOf(type)
        }
    }

    data object KanjiMenu: Screen("kanji_menu")

    data object LevelDetails: Screen("level_details/{levelId}") {

        fun createRoute(levelId: Int) = "level_details/$levelId"
    }
    data object Settings: Screen("settings")
}