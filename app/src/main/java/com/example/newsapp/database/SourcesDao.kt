package com.example.newsapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.newsapp.model.SourcesItem

@Dao
interface SourcesDao {

    @Query("select * from SourcesItem")
    suspend fun getSources(): List<SourcesItem?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSources(sources: List<SourcesItem?>?)

    @Query("select * from SourcesItem where category=:category")
    suspend fun getSourcesByCategoryId(category: String): List<SourcesItem>
}