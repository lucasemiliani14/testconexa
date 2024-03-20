package com.example.usernewstest.api

import com.example.usernewstest.data.Post
import com.example.usernewstest.data.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val usersApi: UsersApi by lazy { retrofit.create(UsersApi::class.java) }
    private val postsApi: PostsApi by lazy { retrofit.create(PostsApi::class.java) }


    suspend fun getUsers(): List<User> {
        val response = usersApi.getUsers()
        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            throw Exception(response.message())
        }
    }

    suspend fun getPosts(): List<Post> {
        val response = postsApi.getPosts()
        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            throw Exception(response.message())
        }
    }



}