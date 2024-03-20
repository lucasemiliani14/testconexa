package com.example.usernewstest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usernewstest.api.ApiClient
import com.example.usernewstest.data.User
import kotlinx.coroutines.launch

class UsersListViewModel() : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users
    private val apiClient =  ApiClient()

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            val response = apiClient.getUsers()
            _users.value = response
        }
    }




}