package com.example.newsapp.di

import android.content.Context
import com.example.newsapp.OnlineHandler
import com.example.newsapp.OnlineHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UtilsModule {

    @Provides
    fun provideOnlineHandler(@ApplicationContext context: Context): OnlineHandler {
        return OnlineHandlerImpl(context)
    }
}