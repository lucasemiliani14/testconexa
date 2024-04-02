package com.example.usernewstest.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import com.example.usernewstest.R
import com.example.usernewstest.data.Address
import com.example.usernewstest.data.Post
import com.example.usernewstest.navigation.AppScreens
import com.example.usernewstest.viewmodels.PostsListViewModel
import com.example.usernewstest.viewmodels.UsersListViewModel

@Composable
fun MainScreen(navController: NavController) {
    val activity = LocalContext.current as ViewModelStoreOwner
    val usersListViewModel = ViewModelProvider(activity)[UsersListViewModel::class.java]
    val postsListViewModel = ViewModelProvider(activity)[PostsListViewModel::class.java]

    BodyContent(navController = navController, postsListViewModel, usersListViewModel)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyContent(navController: NavController, postsListViewModel: PostsListViewModel, usersListViewModel: UsersListViewModel) {

    val tabs = listOf(stringResource(id = R.string.noticias), stringResource(id = R.string.usuarios))
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text(stringResource(id = R.string.top_bar_text_main_screen)) } )
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

        ViewPager(selectedTabIndex = selectedTabIndex, usersListViewModel, postsListViewModel, navController)

    }
}

@Composable
fun ViewPager(selectedTabIndex: Int, usersViewModel: UsersListViewModel, postsListViewModel: PostsListViewModel,
              navController: NavController) {
    val tabs = listOf("Noticias", "Usuarios")
    val currentTab = tabs[selectedTabIndex]
    when (currentTab) {
        "Noticias" -> PostsScreen(postsListViewModel, navController)
        "Usuarios" -> UsersScreen(usersViewModel)
    }
}



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
            .padding(16.dp)
            .testTag("search_bar"),
        label = { Text(stringResource(id = R.string.buscar_search_bar)) },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
        )
    )
}


@Composable
fun PostsScreen(postsListViewModel: PostsListViewModel, navController: NavController) {
    val posts by postsListViewModel.posts.observeAsState(initial = emptyList())
    var searchText by remember { mutableStateOf("") }

    val filteredPosts = remember(posts, searchText) {
        posts.filter { post -> post.title.contains(searchText, ignoreCase = true) }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            searchText = searchText,
            onSearchTextChanged = { searchText = it }
        )
        LazyColumn(modifier = Modifier.weight(1f).testTag("posts_list")) {
            items(filteredPosts.size) { index ->
                val post = filteredPosts[index]
                Text(
                    text = post.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .testTag("post")
                        .clickable { onPostClicked(navController, post) }
                )
            }
        }
    }
}


@Composable
fun UsersScreen(usersViewModel: UsersListViewModel) {
    val users by usersViewModel.users.observeAsState(initial = emptyList())
    val usersCount = users.size
    val context = LocalContext.current
    val handleClick: (Int) -> Unit = { onUserClicked(users[it].address, context) }
    RecyclerView(
        itemCount = usersCount,
        modifier = Modifier.fillMaxSize(),
        handleClick
    ) { index ->
        "${users[index].firstname} ${users[index].lastname}"
    }
}


fun onPostClicked(navController: NavController, post: Post) {
    val title = post.title
    val content = post.content

    navController.navigate(AppScreens.PostDetail.route + "/$title/$content")

}

fun onUserClicked(adress: Address, context: Context) {
    val uri = Uri.parse("geo:${adress.geo.lat},${adress.geo.lng}")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    intent.setPackage("com.google.android.apps.maps")
    context.startActivity(intent)
}

@Composable
fun RecyclerView(
    itemCount: Int,
    modifier: Modifier = Modifier,
    clickAction: (Int) -> Unit = {},
    content: @Composable (index: Int) -> String,

    ) {
    LazyColumn(modifier = modifier.testTag("user_list")) {
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
                        modifier = Modifier
                            .clickable(onClick = { clickAction(index) })
                            .padding(16.dp)
                            .align(Alignment.CenterEnd)
                    )
                }
            }
        }
    }
}

