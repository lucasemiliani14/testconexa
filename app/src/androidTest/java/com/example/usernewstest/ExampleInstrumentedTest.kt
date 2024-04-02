package com.example.usernewstest

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun check_tabBarIsDisplayedUsers() {
        composeRule.onNodeWithText("Usuarios").assertExists()
    }

    @Test
    fun check_tabBarIsDisplayedNews() {
        composeRule.onNodeWithText("Noticias").assertExists()
    }

    @Test
    fun check_postsListIsDisplayed() {
        composeRule.onNodeWithTag("posts_list").assertExists()
    }

    @Test
    fun check_usersListIsDisplayed() {
        composeRule.onNodeWithText("Usuarios").performClick()
        composeRule.onNodeWithTag("user_list").assertExists()
    }

    @Test
    fun check_searchBarFunction() {
        composeRule.onNodeWithText("Buscar").performTextInput("Lorem")
        composeRule.onNodeWithText("Buscar").assertTextContains("Lorem")

    }

    @Test
    fun check_tabBarUserClick() {
        composeRule.onNodeWithText("Usuarios").assertHasClickAction()
    }

    @Test
    fun check_tabBarPostClick() {
        composeRule.onNodeWithText("Noticias").assertHasClickAction()
    }



}