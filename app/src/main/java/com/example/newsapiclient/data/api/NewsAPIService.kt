package com.example.newsapiclient.data.api

import com.example.newsapiclient.BuildConfig
import com.example.newsapiclient.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country")
        country: String,
        @Query("page")
        page: Int,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): Response<ApiResponse>

    @GET("v2/top-headlines")
    suspend fun getSearchedTopHeadlines(
        @Query("country")
        country: String,
        @Query("q")
        searchQuery: String,
        @Query("page")
        page: Int,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): Response<ApiResponse>

}