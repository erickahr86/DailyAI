package com.erick.herrera.daylyai.articles.data

import com.erick.herrera.daylyai.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {

    @GET("top-headlines")
    suspend fun getArticles(
        @Query("country") country: String = "us",
        @Query("category") category: String = "business",
        @Query("apyKey") apiKey: String = BuildConfig.NEWS_API_KEY
    ): ArticlesResponse
}
