package com.example.usernewstest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usernewstest.api.ApiClient
import com.example.usernewstest.data.Post
import kotlinx.coroutines.launch

class PostsListViewModel : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts
    private val apiClient =  ApiClient()

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            val response = apiClient.getPosts()
            _posts.value = response
        }
    }



}