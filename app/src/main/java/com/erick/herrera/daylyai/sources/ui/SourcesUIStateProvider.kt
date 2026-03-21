package com.erick.herrera.daylyai.sources.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.erick.herrera.daylyai.sources.presentation.Source
import com.erick.herrera.daylyai.sources.presentation.SourceUIState

class SourcesUIStateProvider : PreviewParameterProvider<SourceUIState> {
    override val values: Sequence<SourceUIState> = sequenceOf(
        SourceUIState.Loading,
        SourceUIState.Success(
            sources = listOf(
                Source(
                    id = "abc-news",
                    title = "ABC News",
                    description = "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
                    url = "https://abcnews.go.com"
                ),
                Source(
                    id = "bbc-news",
                    title = "BBC News",
                    description = "Breaking news and top stories from the UK and around the world.",
                    url = "https://bbc.com/news"
                )
            )
        ),
        SourceUIState.Error("Failed to load sources")
    )
}
