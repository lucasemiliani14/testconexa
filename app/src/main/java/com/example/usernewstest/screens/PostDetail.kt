package com.example.usernewstest.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.usernewstest.R

@Composable
fun PostDetail(navController: NavController, title: String?, content: String?) {

    BodyContent(navController = navController, title = title, content = content)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyContent(navController: NavController, title: String?, content: String?) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()))
    {
        TopAppBar(title = { Text(stringResource(id = R.string.top_bar_post_detail)) })
        Spacer(modifier = Modifier.height(16.dp))

        title?.let {
            Text(text = it,
                Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
                lineHeight = 38.sp
                )
        }

        Spacer(modifier = Modifier.height(32.dp))


        content?.let {
            Text(text = it,
                Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                textAlign = TextAlign.Center,)
        }

        Spacer(modifier = Modifier.height(140.dp))


    }
}