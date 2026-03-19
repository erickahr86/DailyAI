package com.erick.herrera.daylyai.articles.presentation

import com.erick.herrera.daylyai.articles.data.ArticleData
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

data class Article(
    val title: String?,
    val description: String?,
    val imageUrl: String?,
    val date: String?
) {
    companion object {
        fun mapArticle(data: ArticleData): Article = Article(
            title = data.title,
            description = data.description,
            imageUrl = data.imageUrl,
            date = formatDate(data.date)
        )

        private fun formatDate(rawDate: String?): String? {
            if (rawDate.isNullOrBlank()) return rawDate
            return try {
                val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
                formatter.timeZone = TimeZone.getTimeZone("UTC")
                val articleDate = formatter.parse(rawDate) ?: return rawDate
                val articleCal = Calendar.getInstance().apply {
                    time = articleDate
                    set(Calendar.HOUR_OF_DAY, 0)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }
                val todayCal = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, 0)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }
                val diffDays = ((todayCal.timeInMillis - articleCal.timeInMillis) / (24 * 60 * 60 * 1000)).toInt()
                when (diffDays) {
                    0 -> "Today"
                    1 -> "Yesterday"
                    else -> "$diffDays days ago"
                }
            } catch (_: Exception) {
                rawDate
            }
        }
    }
}
