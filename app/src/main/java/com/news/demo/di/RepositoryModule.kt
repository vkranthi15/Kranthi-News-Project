package com.news.demo.di

import android.content.Context
import com.news.demo.data.NewsServices
import com.news.demo.data.db.ArticleDao
import com.news.demo.data.repository.NewsRepository
import com.news.demo.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideNewsRepo(newsServices: NewsServices,
                        articleDao: ArticleDao,
                        context: Context): NewsRepository = NewsRepositoryImpl(newsServices, articleDao, context)
}