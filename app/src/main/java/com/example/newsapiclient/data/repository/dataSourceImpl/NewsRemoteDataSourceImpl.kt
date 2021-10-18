package com.example.newsapiclient.data.repository.dataSourceImpl

import com.example.newsapiclient.data.api.NewsAPIService
import com.example.newsapiclient.data.model.ApiResponse
import com.example.newsapiclient.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService,
): NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country: String, page: Int): Response<ApiResponse> {
        return newsAPIService.getTopHeadlines(country, page)
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Response<ApiResponse> {
        return newsAPIService.getSearchedTopHeadlines(country, searchQuery, page)
    }


}