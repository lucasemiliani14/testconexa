package com.example.usernewstest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.usernewstest.screens.MainScreen
import com.example.usernewstest.screens.PostDetail

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route) {
        composable(AppScreens.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(AppScreens.PostDetail.route + "/{title}/{content}",
            arguments = listOf(navArgument("title") {
                type = NavType.StringType
            },
                navArgument("content") {
                    type = NavType.StringType
                }
            )
        ) {
            PostDetail(navController = navController,
                title = it.arguments?.getString("title"),
                content = it.arguments?.getString("content"))
        }
    }


}