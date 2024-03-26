package com.example.usernewstest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.usernewstest.navigation.AppNavigation
import com.example.usernewstest.ui.theme.UserNewsTestTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UserNewsTestTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                        AppNavigation()
                }
            }
        }
    }
}
