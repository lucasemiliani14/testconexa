package com.example.usernewstest.navigation

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.usernewstest.screens.MainScreen
import com.example.usernewstest.screens.PostDetail
import com.example.usernewstest.viewmodels.PostsListViewModel
import com.example.usernewstest.viewmodels.UsersListViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route) {
        composable(AppScreens.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(AppScreens.PostDetail.route + "/{slug}/{content}",
            arguments = listOf(navArgument("slug") {
                type = NavType.StringType
            },
                navArgument("content") {
                    type = NavType.StringType
                }
            )
        ) {
            PostDetail(navController = navController,
                slug = it.arguments?.getString("slug"),
                content = it.arguments?.getString("content"))
        }
    }


}