package com.erick.herrera.daylyai.sources.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erick.herrera.daylyai.ui.theme.DaylyAITheme

@Composable
fun SourcesScreen() {
    Scaffold(
        topBar = {
            Text(
                text = "Sources",
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
            // Placeholder - screen content to be implemented
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SourcesScreenPreview() {
    DaylyAITheme {
        SourcesScreen()
    }
}
