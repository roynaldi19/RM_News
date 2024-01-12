package com.roynaldi19.rm_news.data.retrofit

import com.roynaldi19.rm_news.data.response.EverythingResponse
import com.roynaldi19.rm_news.data.response.TopHeadlineResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getTopHeadLines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Response<TopHeadlineResponse>

    @GET("everything")
    suspend fun getEverything(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): Response<EverythingResponse>
}