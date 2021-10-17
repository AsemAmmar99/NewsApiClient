package com.example.newsapiclient.domain.repository

import androidx.lifecycle.LiveData
import com.example.newsapiclient.data.model.ApiResponse
import com.example.newsapiclient.data.model.Article
import com.example.newsapiclient.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsHeadlines(country: String, page: Int): Resource<ApiResponse>
    suspend fun getSearchedNews(searchQuery: String): Resource<ApiResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>


}