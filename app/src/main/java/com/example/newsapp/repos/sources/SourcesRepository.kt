package com.example.newsapp.repos.sources

import com.example.newsapp.model.SourcesItem

interface SourcesRepository {
    suspend fun getSources(category: String): List<SourcesItem?>?
}

interface SourcesOnlineDataSource {
    suspend fun getSources(category: String): List<SourcesItem?>?
}

interface SourcesOfflineDataSource {
    suspend fun updateSources(sources: List<SourcesItem?>?)
    suspend fun getSourcesByCategory(category: String): List<SourcesItem>
}