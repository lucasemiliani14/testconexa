package com.example.usernewstest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.usernewstest.data.Post
import com.example.usernewstest.data.User
import com.example.usernewstest.ui.theme.UserNewsTestTheme
import com.example.usernewstest.viewmodels.PostsListViewModel
import com.example.usernewstest.viewmodels.UsersListViewModel


class MainActivity : ComponentActivity() {


    private val usersViewModel: UsersListViewModel by viewModels()
    private val postsListViewModel: PostsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UserNewsTestTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MyApp(usersViewModel, postsListViewModel)
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(usersListViewModel: UsersListViewModel, postsListViewModel: PostsListViewModel) {
    val tabs = listOf("Noticias", "Usuarios")
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text("App de Noticias") })
        Spacer(modifier = Modifier.height(16.dp))
        TabRow(
            selectedTabIndex = selectedTabIndex,
            contentColor = Color.Gray
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = title) }
                )
            }
        }

        ViewPager(selectedTabIndex = selectedTabIndex, usersListViewModel, postsListViewModel)

    }
}

@Composable
fun ViewPager(selectedTabIndex: Int, usersViewModel: UsersListViewModel, postsListViewModel: PostsListViewModel) {
    val tabs = listOf("Noticias", "Usuarios")
    val currentTab = tabs[selectedTabIndex]
    when (currentTab) {
        "Noticias" -> PostsScreen(postsListViewModel)
        "Usuarios" -> UsersScreen(usersViewModel)
    }
}



@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChanged: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = searchText,
        onValueChange = { onSearchTextChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        label = { Text("Buscar") },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
        )
    )
}


@Composable
fun PostsScreen(postsListViewModel: PostsListViewModel) {
    val posts by postsListViewModel.posts.observeAsState(initial = emptyList<Post>())
    var searchText by remember { mutableStateOf("") }

    val filteredPosts = remember(posts, searchText) {
        posts.filter { post -> post.slug.contains(searchText, ignoreCase = true) }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            searchText = searchText,
            onSearchTextChanged = { searchText = it }
        )
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(filteredPosts.size) { index ->
                val post = filteredPosts[index]
                Text(
                    text = post.slug,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}


@Composable
fun UsersScreen(usersViewModel: UsersListViewModel) {
    val users by usersViewModel.users.observeAsState(initial = emptyList<User>())
    val usersCount = users.size
    val handleClick: (Int) -> Unit = { ddsaddss() }
    RecyclerView(
        itemCount = usersCount,
        modifier = Modifier.fillMaxSize(),
        handleClick
    ) { index ->
        "${users[index].firstname} ${users[index].lastname}"
    }
}


fun ddsadd() {
    Log.d("RecyclerView", "PREVIEW")
}fun ddsaddss() {
    Log.d("RecyclerView", "SAPEEEEEEEE")
}

@Composable
fun RecyclerView(
    itemCount: Int,
    modifier: Modifier = Modifier,
    clickAction: (Int) -> Unit = {},
    content: @Composable (index: Int) -> String,

) {
    androidx.compose.foundation.lazy.LazyColumn(modifier = modifier) {
        items(itemCount) { index ->
            Row (Modifier.fillMaxWidth()){
                Text(
                    text = content(index),
                    modifier = Modifier
                        .padding(16.dp)

                )
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location",
                        modifier = Modifier.clickable(onClick = { clickAction(index) })
                            .padding(16.dp)
                            .align(Alignment.CenterEnd)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Row (Modifier.fillMaxWidth()){
        Text(
            text = "content(index)",
            modifier = Modifier
                .padding(16.dp)

        )
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                modifier = Modifier.clickable(onClick = { ddsadd() })
                    .padding(16.dp)
                    .align(Alignment.CenterEnd)
            )
        }

    }
}
