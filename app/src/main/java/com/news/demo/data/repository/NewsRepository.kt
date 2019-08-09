package com.news.demo.data.repository

import com.news.demo.domain.model.Article
import io.reactivex.Single

interface NewsRepository {
    fun getTopNews(country: String, category: String, apiKey: String): Single<List<Article>>
}