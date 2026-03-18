package com.erick.herrera.daylyai.articles.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erick.herrera.daylyai.articles.data.ArticleData
import com.erick.herrera.daylyai.articles.data.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ArticleUIState>(ArticleUIState.Loading)
    val uiState: StateFlow<ArticleUIState> = _uiState.asStateFlow()

    init {
        logState(ArticleUIState.Loading)
        loadArticles()
    }

    private fun loadArticles() {
        viewModelScope.launch {
            _uiState.value = ArticleUIState.Loading
            logState(ArticleUIState.Loading)

            runCatching {
                repository.getArticles()
            }.fold(
                onSuccess = { list ->
                    val articles = list.map(ArticleData::toArticle)
                    val state = ArticleUIState.Success(articles)
                    _uiState.value = state
                    logState(state)
                },
                onFailure = { e ->
                    Timber.e(e, "Failed to load articles")
                    val state = ArticleUIState.Error("Something went wrong, please try again later")
                    _uiState.value = state
                    logState(state)
                }
            )
        }
    }

    private fun logState(state: ArticleUIState) {
        val logMessage = when (state) {
            ArticleUIState.Loading -> "ArticleUIState: Loading"
            is ArticleUIState.Success -> "ArticleUIState: Success | articles=${state.articles.size}"
            is ArticleUIState.Error -> "ArticleUIState: Error | error=${state.message}"
        }
        Timber.d(logMessage)
    }

    fun retry() {
        loadArticles()
    }
}

private fun ArticleData.toArticle(): Article = Article(
    title = title,
    description = description,
    imageUrl = imageUrl,
    date = date
)
