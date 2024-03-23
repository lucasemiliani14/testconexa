package com.example.usernewstest.api

import com.example.usernewstest.data.Address
import com.example.usernewstest.data.Geo
import com.example.usernewstest.data.Post
import com.example.usernewstest.data.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.SSLHandshakeException
import kotlin.random.Random

class ApiClient() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val usersApi: UsersApi by lazy { retrofit.create(UsersApi::class.java) }
    private val postsApi: PostsApi by lazy { retrofit.create(PostsApi::class.java) }


    suspend fun getUsers(): List<User> {
        return try {
            val response = usersApi.getUsers()
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                throw Exception(response.message())
            }
        } catch (e: Exception) {
            crearUsuariosFalsosAleatorios()
        }
    }

    suspend fun getPosts(): List<Post> {
        return try {
            val response = postsApi.getPosts()
            return if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                throw Exception(response.message())
            }
        } catch (e: Exception) {
            crearPostsFalsosAleatorios()
        }
    }

//    suspend fun getUsers(): List<User> {
//        val response = usersApi.getUsers()
//        return if (response.isSuccessful) {
//            response.body() ?: emptyList()
//        } else {
//            throw Exception(response.message())
//        }
//    }
//
//    suspend fun getPosts(): List<Post> {
//        val response = postsApi.getPosts()
//        return if (response.isSuccessful) {
//            response.body() ?: emptyList()
//        } else {
//            throw Exception(response.message())
//        }
//    }

    fun crearUsuariosFalsosAleatorios(): List<User> {
        val nombres = listOf("Ana", "Juan", "María", "Pedro", "Laura", "David", "Marta", "Carlos", "Irene", "Francisco")
        val apellidos = listOf("Pérez", "Gómez", "Martínez", "López", "González", "Fernández", "Rodríguez", "Díaz", "Sánchez", "Ramírez")
        val ciudades = listOf("Madrid", "Barcelona", "Sevilla", "Valencia", "Zaragoza", "Málaga", "Bilbao", "Murcia", "Palma de Mallorca", "Las Palmas de Gran Canaria")
        val calles = listOf("Calle Mayor", "Avenida del Sol", "Gran Vía", "Paseo de la Castellana", "Calle de Alcalá", "Calle de Toledo", "Calle de Serrano", "Calle de Goya", "Calle de Velázquez", "Calle de Jorge Juan")
        val suites = listOf("1A", "2B", "3C", "4D", "5E", "6F", "7G", "8H", "9I", "10J")
        val codigosPostales = listOf("28013", "08001", "41001", "46001", "50001", "35001", "48001", "30001", "07001", "38001")

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

    fun crearPostsFalsosAleatorios(): List<Post> {
        val palabras = listOf("Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit", "sed", "do", "eiusmod")

        return (1..10).map {
            Post(
                id = it,
                slug = "-".repeat(Random.nextInt(5)) + palabras.shuffled().joinToString("-"),
                content = palabras.shuffled().take(Random.nextInt(100)).joinToString(" ")
            )
        }
    }



}