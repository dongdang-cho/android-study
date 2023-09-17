package com.example.exam01.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class BookmarkEntity (
    @PrimaryKey
    val id: Int,
    val name: String,
    val resourceURI: String,
)