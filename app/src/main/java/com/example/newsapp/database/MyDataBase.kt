package com.example.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.model.SourcesItem

@Database(entities = [SourcesItem::class], version = 1, exportSchema = false)
abstract class MyDataBase : RoomDatabase() {
    abstract fun sourcesDao(): SourcesDao

    companion object {
        var database: MyDataBase? = null
        const val DATABASE_NAME = "newsdatabase"
        fun init(context: Context) {
            if (database == null) {
                database = Room.databaseBuilder(
                    context, MyDataBase::class.java, DATABASE_NAME).fallbackToDestructiveMigration()
                    .build()
            }
        }

        fun getInstance(): MyDataBase {
            return database!!
        }
    }
}