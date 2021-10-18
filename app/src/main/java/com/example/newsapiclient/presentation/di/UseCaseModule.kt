package com.example.newsapiclient.presentation.di

import com.example.newsapiclient.domain.repository.NewsRepository
import com.example.newsapiclient.domain.repository.NewsRepositoryImpl
import com.example.newsapiclient.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newsapiclient.domain.usecase.GetSearchedNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetNewsheadlinesUseCase(
        newsRepository: NewsRepository
    ): GetNewsHeadlinesUseCase{
        return GetNewsHeadlinesUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideGetSearchedNewsUseCase(
        newsRepository: NewsRepository
    ): GetSearchedNewsUseCase{
        return GetSearchedNewsUseCase(newsRepository)
    }
}