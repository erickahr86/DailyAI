package com.erick.herrera.daylyai.articles.data

import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val api: ArticleApi
) {

    suspend fun getArticles(): List<ArticleData> {
        val response = api.getArticles()
        return response.articles ?: emptyList()
    }
}
