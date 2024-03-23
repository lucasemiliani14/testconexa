package com.example.usernewstest.navigation

sealed class AppScreens(val route: String) {
    object MainScreen: AppScreens("main_screen")

    object PostDetail: AppScreens("post_detail")

}