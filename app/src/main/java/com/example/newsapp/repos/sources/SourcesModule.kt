package com.example.newsapp.repos.sources

import com.example.newsapp.OnlineHandler
import com.example.newsapp.api.WebServices
import com.example.newsapp.database.MyDataBase
import com.example.newsapp.repos.sources.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SourcesModule {

    @Provides
    fun provideOnlineDataSource(webServices: WebServices): SourcesOnlineDataSource {
        return SourcesOnlineDataSourceImpl(webServices)
    }

    @Provides
    fun provideOfflineDataSource(dataBase: MyDataBase): SourcesOfflineDataSource {
        return SourcesOfflineDataSourceImpl(dataBase)
    }

    @Singleton
    @Provides
    fun provideDataBase(): MyDataBase {
        return MyDataBase.getInstance()
    }

    @Provides
    fun provideSourcesRepo(
        onlineDataSource: SourcesOnlineDataSource,
        offlineDataSource: SourcesOfflineDataSource,
        onlineHandler: OnlineHandler,
    ): SourcesRepository {
        return SourcesRepositoryImpl(onlineDataSource, offlineDataSource, onlineHandler)
    }


}