package com.erick.herrera.daylyai.articles.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.erick.herrera.daylyai.articles.presentation.Article
import com.erick.herrera.daylyai.articles.presentation.ArticleUIState
import com.erick.herrera.daylyai.articles.presentation.ArticleViewModel
import com.erick.herrera.daylyai.ui.theme.DaylyAITheme

@Composable
fun ArticleScreen(
    viewModel: ArticleViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ArticleScreenContent(uiState = uiState)
}

@Composable
fun ArticleScreenContent(
    uiState: ArticleUIState
) {
    Scaffold(
        topBar = {
            Text(
                text = "Articles",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (uiState) {
                ArticleUIState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is ArticleUIState.Error -> {
                    Text(
                        text = uiState.message ?: "Something went wrong",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    )
                }

                is ArticleUIState.Success -> {
                    ArticleList(
                        articles = uiState.articles,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
private fun ArticleList(
    articles: List<Article>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = articles,
            key = { "${it.title}-${it.date}" }
        ) { article ->
            ArticleListItem(article = article)
        }
    }
}

@Composable
private fun ArticleListItem(
    article: Article,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = article.imageUrl,
                contentDescription = article.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = article.title.orEmpty(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                if (!article.description.isNullOrBlank()) {
                    Text(
                        text = article.description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp),
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                if (!article.date.isNullOrBlank()) {
                    Text(
                        text = article.date,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = 12.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleScreenPreview(
    @PreviewParameter(ArticleUIStateProvider::class) uiState: ArticleUIState
) {
    DaylyAITheme {
        ArticleScreenContent(uiState = uiState)
    }
}
