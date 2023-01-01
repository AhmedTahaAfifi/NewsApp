package com.example.newsapp.repos.sources

import com.example.newsapp.Constants
import com.example.newsapp.api.WebServices
import com.example.newsapp.model.SourcesItem

class SourcesOnlineDataSourceImpl(val webServices: WebServices) : SourcesOnlineDataSource {
    override suspend fun getSources(category: String): List<SourcesItem?>? {
        try {
            val result = webServices.getSources(Constants.apiKey, category)
            return result.sources
        } catch (ex: Exception) {
            throw ex
        }
    }
}