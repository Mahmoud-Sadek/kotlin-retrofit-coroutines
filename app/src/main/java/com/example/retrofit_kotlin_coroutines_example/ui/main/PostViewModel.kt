package com.example.retrofit_kotlin_coroutines_example.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.retrofit_kotlin_coroutines_example.data.PostsClient
import com.example.retrofit_kotlin_coroutines_example.utils.Resource
import kotlinx.coroutines.Dispatchers

class PostViewModel : ViewModel() {

    fun getPosts() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = PostsClient.iNSTANCE?.getPosts()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}