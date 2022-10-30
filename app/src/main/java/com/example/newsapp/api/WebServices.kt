package com.example.newsapp.api

import com.example.newsapp.model.NewsResponse
import com.example.newsapp.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("v2/top-headlines/sources")
    fun getSources(
        @Query("apiKey") apiKey: String,
        @Query("category") category: String
    ): Call<SourcesResponse>

    @GET("v2/everything")
    fun getNews(
        @Query("apiKey") apiKey: String,
        @Query("sources") soursces: String
    ): Call<NewsResponse>
}