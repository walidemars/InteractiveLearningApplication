package com.nikitinsky.interactive.learning.app.presentation.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nikitinsky.interactive.learning.app.presentation.screens.levelcontent.LevelContentScreen
import com.nikitinsky.interactive.learning.app.presentation.screens.levels.LevelsScreen
import com.nikitinsky.interactive.learning.app.presentation.screens.practice.PracticeScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Levels.route
    ) {
        composable(
            Screen.Levels.route
        ) {
            LevelsScreen(
                onLevelClick = {
                    navController.navigate(Screen.LevelContent.createRoute(it.id))
                }
            )
        }
        composable(
            route = Screen.LevelContent.route
        ) {
            val levelId = Screen.LevelContent.getLevelId(it.arguments)
            LevelContentScreen(
                levelId = levelId,
                onBackClick = {
                    navController.popBackStack()
                },
                onPracticeClick = {
                    navController.navigate(Screen.Practice.createRoute(levelId))
                }
            )
        }
        composable(
            route = Screen.Practice.route
        ) {
            val levelId = Screen.Practice.getLevelId(it.arguments)
            PracticeScreen(
                levelId = levelId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

sealed class Screen(val route: String) {

    data object Levels: Screen(route = "levels")

    data object LevelContent: Screen(route = "level/{level_id}") {
        fun createRoute(levelId: Int): String {
            return "level/$levelId"
        }

        fun getLevelId(arguments: Bundle?): Int {
            return arguments?.getString("level_id")?.toInt() ?: 1
        }
    }

    data object Practice: Screen(route = "practice/{level_id}") {
        fun createRoute(levelId: Int): String {
            return "practice/$levelId"
        }

        fun getLevelId(arguments: Bundle?): Int {
            return arguments?.getString("level_id")?.toInt() ?: 1
        }
    }
}