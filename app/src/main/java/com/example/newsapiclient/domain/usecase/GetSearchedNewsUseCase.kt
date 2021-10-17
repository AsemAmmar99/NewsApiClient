package com.example.newsapiclient.domain.usecase

import com.example.newsapiclient.data.model.ApiResponse
import com.example.newsapiclient.data.util.Resource
import com.example.newsapiclient.domain.repository.NewsRepository
import java.time.temporal.TemporalQuery

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(searchQuery: String): Resource<ApiResponse>{
        return newsRepository.getSearchedNews(searchQuery)
    }
}