package com.example.exam01.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.exam01.network.response.Comics
import com.example.exam01.network.response.Events
import com.example.exam01.network.response.Series
import com.example.exam01.network.response.Stories
import com.example.exam01.network.response.Thumbnail
import com.example.exam01.network.response.Url

@Entity(tableName = "bookmark")
data class BookmarkEntity (
    @PrimaryKey
    val id: Int,
    val name: String,
    val resourceURI: String,
)