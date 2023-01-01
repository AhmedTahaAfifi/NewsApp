package com.example.newsapp.ui.news

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object AdapterModule {


    @Provides
    fun provideNewsAdepter(): NewsAdapter {
        return NewsAdapter(null)
    }

}