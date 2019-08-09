package com.news.demo.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.news.demo.domain.model.Article

@Database(
        entities = [
            Article::class
        ],
        version = 1,
        exportSchema = false)
abstract class AndroidNewsDb : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}