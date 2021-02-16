package com.example.retrofit_kotlin_coroutines_example.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PostResponse {

    @Expose
    @SerializedName("body")
    val body: String? = null

    @Expose
    @SerializedName("title")
    var title: String? = null

    @Expose
    @SerializedName("id")
    var id = 0

    @Expose
    @SerializedName("userId")
    var userid = 0


}