package com.erick.herrera.daylyai.sources.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erick.herrera.daylyai.sources.data.SourcesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(
    private val repository: SourcesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SourceUIState>(SourceUIState.Loading)
    val uiState: StateFlow<SourceUIState> = _uiState.asStateFlow()

    init {
        logState(SourceUIState.Loading)
        loadSources()
    }

    private fun loadSources() {
        viewModelScope.launch {
            _uiState.value = SourceUIState.Loading
            logState(SourceUIState.Loading)

            runCatching {
                repository.getSources()
            }.fold(
                onSuccess = { list ->
                    val sources = list.map(Source::mapSource)
                    val state = SourceUIState.Success(sources)
                    _uiState.value = state
                    logState(state)
                },
                onFailure = { e ->
                    Timber.e(e, "Failed to load sources")
                    val state = SourceUIState.Error("Something went wrong, please try again later")
                    _uiState.value = state
                    logState(state)
                }
            )
        }
    }

    private fun logState(state: SourceUIState) {
        val logMessage = when (state) {
            SourceUIState.Loading -> "SourceUIState: Loading"
            is SourceUIState.Success -> "SourceUIState: Success | sources=${state.sources.size}"
            is SourceUIState.Error -> "SourceUIState: Error | error=${state.message}"
        }
        Timber.d(logMessage)
    }

    fun retry() {
        loadSources()
    }
}
