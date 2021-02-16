package com.example.retrofit_kotlin_coroutines_example.data

import com.example.retrofit_kotlin_coroutines_example.pojo.PostResponse
import retrofit2.http.GET

interface PostInterface {

    @GET("posts")
    suspend fun GetPosts(): List<PostResponse>
}