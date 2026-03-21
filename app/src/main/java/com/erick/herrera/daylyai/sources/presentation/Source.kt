package com.erick.herrera.daylyai.sources.presentation

import com.erick.herrera.daylyai.sources.data.SourceData

data class Source(
    val id: String?,
    val title: String?,
    val description: String?,
    val url: String?
) {
    companion object {
        fun mapSource(data: SourceData): Source = Source(
            id = data.id,
            title = data.name,
            description = data.description,
            url = data.url
        )
    }
}
