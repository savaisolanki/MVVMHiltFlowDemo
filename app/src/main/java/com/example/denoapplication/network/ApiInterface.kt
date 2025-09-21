package com.example.denoapplication.network

import com.example.denoapplication.model.PostResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("posts")
    suspend fun getPosts(): Response<List<PostResponseItem>>
}
