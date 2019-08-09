package com.news.demo.data.repository

import android.content.Context
import com.github.ajalt.timberkt.d
import com.news.demo.data.NewsServices
import com.news.demo.data.db.ArticleDao
import com.news.demo.domain.model.Article
import com.news.demo.utils.isNetworkStatusAvailable
import com.task.utils.AppConstants
import io.reactivex.Single

class NewsRepositoryImpl(private val service: NewsServices,
                         private val articleDao: ArticleDao,
                         private val context: Context) : NewsRepository {

    override fun getTopNews(country: String, category: String, apiKey: String): Single<List<Article>> {
        if (context.isNetworkStatusAvailable()) {
            return service.getTopHeadlines(country = country, category = category, apiKey = AppConstants.API_KEY)
                    .flattenAsObservable { it.articles }
                    .map {
                        val article = Article(
                                id = 0,
                                title = it.title,
                                author = it.author ?: "",
                                image = it.urlToImage ?: "",
                                publishedAt = it.publishedAt,
                                sourceId = it.source.id ?: "",
                                sourceName = it.source.name,
                                url = it.url
                        )
                        d { "insert article ${article.title}" }
                        articleDao.insert(article)
                        article
                    }.toList()
        } else {
            return articleDao.getArticles()
        }
    }
}