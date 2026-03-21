package com.erick.herrera.daylyai.sources.data

import javax.inject.Inject

class SourcesRepository @Inject constructor(
    private val api: SourcesApi
) {

    suspend fun getSources(): List<SourceData> {
        val response = api.getSources()
        return response.sources ?: emptyList()
    }
}
