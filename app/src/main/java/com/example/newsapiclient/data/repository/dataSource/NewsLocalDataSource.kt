package com.example.newsapiclient.data.repository.dataSource

import com.example.newsapiclient.data.model.Article

interface NewsLocalDataSource {
    suspend fun saveArticleToDB(article: Article)
}