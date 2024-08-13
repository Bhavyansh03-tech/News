package com.example.news.api

import com.example.news.apiModels.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("v2/top-headlines")
    fun getData(
        @Query("country")
        country: String,
        @Query("category")
        category: String,
        @Query("page")
        page: Int,
        @Query("apiKey")
        apiKey: String
    ): Call<NewsResponse>
}