package com.example.usernewstest.util

import com.example.usernewstest.data.Address
import com.example.usernewstest.data.Geo
import com.example.usernewstest.data.Post
import com.example.usernewstest.data.User

object Util {
    fun mockPosts(): List<Post> {
        val palabras = listOf(
            "lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit",
            "sed", "do", "eiusmod", "tempor", "incididunt", "ut"
        )
        val posts = mutableListOf<Post>()
        for (i in 0 until 14) {
            val title = generateRandomtitle(palabras)
            val content = generateRandomContent(palabras)
            posts.add(Post(i, title, content))
        }
        return posts
    }

    private fun generateRandomtitle(palabras: List<String>): String {
        val randomNumber = (2..6).random()
        return palabras.shuffled().take(randomNumber).joinToString(" ")
    }

    private fun generateRandomContent(palabras: List<String>): String {
        val randomNumber = (40..777).random()
        return (1..randomNumber).joinToString(" ") { palabras.random() }
    }

    fun mockUsers(): List<User> {
        val nombres = listOf("Ana", "Juan", "María", "Pedro", "Laura", "David", "Marta", "Carlos", "Irene", "Francisco")
        val apellidos = listOf("Pérez", "Gómez", "Martínez", "López", "González", "Fernández", "Rodríguez", "Díaz", "Sánchez", "Ramírez")

        return (1..10).map {
            User(
                id = it,
                firstname = nombres.random(),
                lastname = apellidos.random(),
                address = Address(
                    street = "Rue de Rivoli",
                    suite = "4A",
                    city = "Paris",
                    zipcode = "75001",
                    geo = Geo(
                        lat = "48.858257",
                        lng = "2.294528"
                    )
                )
            )
        }
    }

}