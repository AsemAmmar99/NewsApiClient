package com.example.newsapiclient.domain.usecase

import com.example.newsapiclient.data.model.Article
import com.example.newsapiclient.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.deleteNews(article)
}