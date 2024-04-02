package com.example.usernewstest

import com.example.usernewstest.api.ApiClient
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ApiCallsTests {

    @Test
    fun test_posts_call()  = runTest {
        val apiClient = ApiClient()

        val posts = apiClient.getPosts()

        TestCase.assertTrue(posts.isNotEmpty())

    }

    @Test
    fun test_users_call()  = runTest {
        val apiClient = ApiClient()

        val posts = apiClient.getUsers()

        TestCase.assertTrue(posts.isNotEmpty())

    }


}