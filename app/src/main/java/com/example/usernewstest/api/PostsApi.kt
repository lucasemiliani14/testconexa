package com.example.usernewstest.api

import com.example.usernewstest.data.Post
import retrofit2.Response
import retrofit2.http.GET

interface PostsApi {

    @GET("/posts")
    suspend fun getPosts() : Response<List<Post>>


}