package com.example.newsapiclient.domain.usecase

import com.example.newsapiclient.data.model.Article
import com.example.newsapiclient.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
    fun execute(): Flow<List<Article>>{
        return newsRepository.getSavedNews()
    }
}