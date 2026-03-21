package com.erick.herrera.daylyai.sources.presentation

sealed class SourceUIState {
    data object Loading : SourceUIState()
    data class Success(val sources: List<Source>) : SourceUIState()
    data class Error(val message: String?) : SourceUIState()
}
