package com.news.demo.data

import com.news.demo.data.model.response.NewsResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {
    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country: String,
                        @Query("category") category: String,
                        @Query("apiKey") apiKey: String): Single<NewsResponseModel>
}