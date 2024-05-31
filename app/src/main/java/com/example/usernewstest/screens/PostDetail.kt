package com.example.usernewstest.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
        TopAppBar({
            Row {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Arrow Back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
                Spacer(modifier = Modifier.width(14.dp))
                Text(text = stringResource(id = R.string.top_bar_post_detail))
            }
        })
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