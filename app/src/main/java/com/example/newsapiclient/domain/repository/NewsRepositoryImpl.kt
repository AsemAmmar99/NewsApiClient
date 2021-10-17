package com.example.newsapiclient.domain.repository

import com.example.newsapiclient.data.model.ApiResponse
import com.example.newsapiclient.data.model.Article
import com.example.newsapiclient.data.repository.dataSource.NewsRemoteDataSource
import com.example.newsapiclient.data.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource
): NewsRepository {
    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<ApiResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadlines(country, page))
    }

    private fun responseToResource(response: Response<ApiResponse>): Resource<ApiResponse>{
        if (response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getSearchedNews(searchQuery: String): Resource<ApiResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}