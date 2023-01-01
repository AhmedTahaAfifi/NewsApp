package com.example.newsapp.repos.sources

import com.example.newsapp.database.MyDataBase
import com.example.newsapp.model.SourcesItem

class SourcesOfflineDataSourceImpl(val dataBase: MyDataBase) : SourcesOfflineDataSource {
    override suspend fun updateSources(sources: List<SourcesItem?>?) {
        dataBase.sourcesDao().updateSources(sources)
    }

    override suspend fun getSourcesByCategory(category: String): List<SourcesItem> {
        return dataBase.sourcesDao().getSourcesByCategoryId(category)
    }
}