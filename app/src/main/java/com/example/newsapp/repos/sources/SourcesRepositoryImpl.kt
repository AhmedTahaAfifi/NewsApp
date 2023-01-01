package com.example.newsapp.repos.sources

import com.example.newsapp.OnlineHandler
import com.example.newsapp.model.SourcesItem

class SourcesRepositoryImpl(
    val sourcesOnlineDataSource: SourcesOnlineDataSource,
    val sourcesOfflineDataSource: SourcesOfflineDataSource,
    val onlineHandler: OnlineHandler,
) : SourcesRepository {
    override suspend fun getSources(category: String): List<SourcesItem?>? {
        try {
            if (onlineHandler.isOnline()) {
                val source = sourcesOnlineDataSource.getSources(category)
                sourcesOfflineDataSource.updateSources(source)
                return source
            } else {
                return sourcesOfflineDataSource.getSourcesByCategory(category)
            }

        } catch (ex: Exception) {
            return sourcesOfflineDataSource.getSourcesByCategory(category)
        }
    }
}