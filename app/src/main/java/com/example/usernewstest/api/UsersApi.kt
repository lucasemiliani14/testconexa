package com.example.usernewstest.api

import com.example.usernewstest.data.User
import retrofit2.Response
import retrofit2.http.GET

interface UsersApi {

    @GET("/users")
    suspend fun getUsers() : Response<List<User>>
}