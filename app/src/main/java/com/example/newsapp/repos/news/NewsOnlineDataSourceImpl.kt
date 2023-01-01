package com.example.newsapp.repos.news

import com.example.newsapp.Constants
import com.example.newsapp.api.WebServices
import com.example.newsapp.model.ArticlesItem

class NewsOnlineDataSourceImpl(val webServices: WebServices) : NewsOnlineDataSource {
    override suspend fun getNewsBySource(sourceId: String): List<ArticlesItem?>? {
        try {
            val result = webServices.getNews(Constants.apiKey, sourceId)
            return result.articles
        } catch (ex: Exception) {
            throw ex
        }

    }
}