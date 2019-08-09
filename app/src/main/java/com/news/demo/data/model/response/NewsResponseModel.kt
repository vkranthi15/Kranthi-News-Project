package com.news.demo.data.model.response

import com.news.demo.data.model.ArticleModel

data class NewsResponseModel(
        val status: String,
        val totalResults: Int,
        val articles: List<ArticleModel>
)