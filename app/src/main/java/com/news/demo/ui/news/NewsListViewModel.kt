package com.news.demo.ui.news

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.ajalt.timberkt.d
import com.github.ajalt.timberkt.e
import com.news.demo.data.repository.NewsRepository
import com.news.demo.domain.model.Article
import com.task.utils.AppConstants.INSTANCE.API_KEY
import com.task.utils.AppConstants.INSTANCE.category
import com.task.utils.AppConstants.INSTANCE.country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsListViewModel @Inject constructor(private val repo: NewsRepository) : ViewModel() {
    // declare state for news list
    val stateLiveData = MutableLiveData<NewsListState>()

    // initiate state for news list
    init {
        stateLiveData.value = LoadingState(emptyList(), false)
    }

    fun updateNewsList() {
        d { "update news list" }
        getNewsList()
    }

    fun restoreNewsList() {
        d { "restore news list" }
        stateLiveData.value = DefaultState(obtainCurrentData(), true)
    }

    fun refreshNewsList() {
        stateLiveData.value = LoadingState(emptyList(), false)
        getNewsList()
    }

    private fun getNewsList() {
        val result = repo.getTopNews(country = country, category = category, apiKey = API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNewsReceived, this::onError)
    }

    private fun onError(error: Throwable) {
        e { "error ${error.localizedMessage}" }
        stateLiveData.value = ErrorState(error.localizedMessage, obtainCurrentData(), false)
    }

    private fun onNewsReceived(news: List<Article>) {
        d { "data received ${news.size}" }
        val currentNews = obtainCurrentData().toMutableList()
        currentNews.addAll(news)
        stateLiveData.value = DefaultState(currentNews, true)
    }

    private fun obtainCurrentData() = stateLiveData.value?.data ?: emptyList()

    private fun obtainCurrentLoadedAllItems() = stateLiveData.value?.loadedAllItems ?: false
}
