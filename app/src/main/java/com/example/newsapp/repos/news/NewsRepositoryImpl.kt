package com.example.newsapp.repos.news

import com.example.newsapp.model.ArticlesItem

class NewsRepositoryImpl(val newsOnlineDataSource: NewsOnlineDataSource) : NewsRepository {
    override suspend fun getNew(sourceId: String): List<ArticlesItem?>? {
        try {
            val result = newsOnlineDataSource.getNewsBySource(sourceId)
            return result
        } catch (ex: Exception) {
            throw ex
        }

    }
}