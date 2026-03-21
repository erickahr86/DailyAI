package com.erick.herrera.daylyai.sources.data

import com.erick.herrera.daylyai.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface SourcesApi {

    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("country") country: String = "us",
        @Query("category") category: String = "business",
        @Query("apyKey") apiKey: String = BuildConfig.NEWS_API_KEY
    ): SourcesResponse
}
