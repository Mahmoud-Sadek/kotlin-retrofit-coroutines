package com.example.retrofit_kotlin_coroutines_example.data

import com.example.retrofit_kotlin_coroutines_example.pojo.PostResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostsClient {
    private val postInterface: PostInterface
//    val posts: List<PostResponse>
//        get() = postInterface.GetPosts()
    suspend fun getPosts() = postInterface.GetPosts()

    companion object {
        private const val BASE_URL = "http://jsonplaceholder.typicode.com/"
        var iNSTANCE: PostsClient? = null
            get() {
                if (null == field) {
                    field = PostsClient()
                }
                return field
            }
            private set
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        postInterface = retrofit.create(PostInterface::class.java)
    }
}