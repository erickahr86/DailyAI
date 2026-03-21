package com.erick.herrera.daylyai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.erick.herrera.daylyai.ui.MainScreen
import com.erick.herrera.daylyai.ui.theme.DaylyAITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DaylyAITheme {
                MainScreen()
            }
        }
    }
}
