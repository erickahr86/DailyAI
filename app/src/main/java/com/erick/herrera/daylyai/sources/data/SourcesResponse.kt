package com.erick.herrera.daylyai.sources.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SourcesResponse(
    val status: String?,
    val sources: List<SourceData>?
)
