package com.example.newsapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.model.ArticlesItem

@Dao
interface NewsDao {

    @Query("select * from ArticlesItem")
    suspend fun getNews(): List<ArticlesItem?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNews(news: List<ArticlesItem?>?)

    @Query("select * from Source where name =:sources")
    suspend fun getNewsBySourcesId(sources: String): List<ArticlesItem?>

}