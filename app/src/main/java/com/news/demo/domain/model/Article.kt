package com.news.demo.domain.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import java.io.Serializable

@Entity(
        tableName = "article",
        indices = [
            (Index("url"))],
        primaryKeys = ["url"]
)
data class Article(
        val id: Int,
        val title: String,
        val author: String,
        val sourceId: String,
        val sourceName: String,
        val url: String,
        val image: String,
        val publishedAt: String
) : Serializable