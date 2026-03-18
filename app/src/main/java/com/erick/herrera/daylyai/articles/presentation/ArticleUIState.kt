package com.erick.herrera.daylyai.articles.presentation

sealed class ArticleUIState {
    data object Loading : ArticleUIState()
    data class Success(val articles: List<Article>) : ArticleUIState()
    data class Error(val message: String?) : ArticleUIState()
}
