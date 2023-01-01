package com.example.newsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class NewsResponse(

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<ArticlesItem?>? = null,

    @field:SerializedName("status")
    val status: String? = null
)

@Entity
data class ArticlesItem(

    @ColumnInfo
    @field:SerializedName("publishedAt")
    val publishedAt: String? = null,

    @ColumnInfo
    @field:SerializedName("author")
    val author: String? = null,

    @ColumnInfo
    @field:SerializedName("urlToImage")
    val urlToImage: String? = null,

    @ColumnInfo
    @field:SerializedName("description")
    val description: String? = null,

    @ColumnInfo
    @field:SerializedName("source")
    val source: Source? = null,

    @ColumnInfo
    @field:SerializedName("title")
    val title: String? = null,

    @ColumnInfo
    @field:SerializedName("url")
    val url: String? = null,

    @ColumnInfo
    @field:SerializedName("content")
    val content: String? = null,
)

@Entity
data class Source(

    @ColumnInfo
    @field:SerializedName("name")
    val name: String? = null,

    @PrimaryKey
    @ColumnInfo
    @field:SerializedName("id")
    val id: String? = null,
)
