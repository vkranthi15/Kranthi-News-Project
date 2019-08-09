package com.news.demo.ui.news

import com.news.demo.domain.model.Article

sealed class NewsListState {
    abstract val data: List<Article>
    abstract val loadedAllItems: Boolean
}

data class DefaultState(override val data: List<Article>, override val loadedAllItems: Boolean) : NewsListState()
data class LoadingState(override val data: List<Article>, override val loadedAllItems: Boolean) : NewsListState()
data class ErrorState(val errorMessage: String, override val data: List<Article>, override val loadedAllItems: Boolean) : NewsListState()