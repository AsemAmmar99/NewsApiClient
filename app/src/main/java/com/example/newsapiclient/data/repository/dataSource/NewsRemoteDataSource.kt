package com.example.newsapiclient.data.repository.dataSource

import com.example.newsapiclient.data.model.ApiResponse
import retrofit2.Response

interface NewsRemoteDataSource {

    suspend fun getTopHeadlines(country: String, page: Int): Response<ApiResponse>
    suspend fun getSearchedNews(country: String, searchQuery: String, page: Int): Response<ApiResponse>

}