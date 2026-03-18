package com.erick.herrera.daylyai.articles.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleData(
    val title: String?,
    val description: String?,
    @Json(name = "urlToImage")
    val imageUrl: String?,
    @Json(name = "publishedAt")
    val date: String?
)
