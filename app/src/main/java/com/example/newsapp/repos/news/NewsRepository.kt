package com.example.newsapp.repos.news

import com.example.newsapp.model.ArticlesItem

interface NewsRepository {
    suspend fun getNew(sourceId: String): List<ArticlesItem?>?
}

interface NewsOnlineDataSource {
    suspend fun getNewsBySource(sourceId: String): List<ArticlesItem?>?
}