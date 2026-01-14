package com.nikitinsky.interactive.learning.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nikitinsky.interactive.learning.app.presentation.screens.level_details.LevelDetailsScreen
import com.nikitinsky.interactive.learning.app.presentation.screens.levels_menu.LevelsMenuScreen
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
                    navController.navigate(Screen.LevelsMenu.createRoute("hiragana"))
                },
                onNavigateToKatakana = {
                    navController.navigate(Screen.LevelsMenu.createRoute("katakana"))
                },
                onNavigateToKanji = {

                },
                onNavigateToSettings = {

                },
                onNavigateToExit = {

                }
            )
        }
        composable(
            route = Screen.LevelsMenu.route,
            arguments = listOf(navArgument("type") { type = NavType.StringType})
        ) { backStackEntry ->
            val alphabetType = backStackEntry.arguments?.getString("type")
                ?: "hiragana"

            LevelsMenuScreen(
                type = alphabetType,
                onLevelClick = { levelId ->
                    navController.navigate(Screen.LevelDetails.createRoute(levelId))
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = Screen.LevelDetails.route,
            arguments = listOf(navArgument("levelId") { type = NavType.StringType})
        ) { backStackEntry ->
            val levelId = backStackEntry.arguments?.getString("levelId")

            LevelDetailsScreen(
                levelId = levelId,
                onBackClick = {
                    navController.popBackStack()
                }
            )

        }
        composable(Screen.Settings.route) {

        }
    }
}

sealed class Screen(val route: String) {

    data object MainMenu: Screen("main_menu")

    data object LevelsMenu: Screen("levels_menu/{type}") {

        fun createRoute(type: String) = "levels_menu/$type"
    }

    data object LevelDetails: Screen("level_details/{levelId}") {

        fun createRoute(levelId: String) = "level_details/$levelId"
    }
    data object Settings: Screen("settings")
}