package com.erick.herrera.daylyai.articles.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.erick.herrera.daylyai.articles.presentation.Article
import com.erick.herrera.daylyai.articles.presentation.ArticleUIState

class ArticleUIStateProvider : PreviewParameterProvider<ArticleUIState> {
    override val values: Sequence<ArticleUIState> = sequenceOf(
        ArticleUIState.Loading,
        ArticleUIState.Success(
            articles = listOf(
                Article(
                    title = "Exploring the Future of AI in Daily Life",
                    description = "Artificial Intelligence is rapidly transforming how we interact with technology and each other on a daily basis.",
                    imageUrl = null,
                    date = "2023-10-27"
                ),
                Article(
                    title = "Top 10 Productivity Tips for Remote Workers",
                    description = "Stay focused and efficient while working from home with these proven strategies and tools.",
                    imageUrl = null,
                    date = "2023-10-26"
                )
            )
        ),
        ArticleUIState.Error("Failed to load articles")
    )
}
