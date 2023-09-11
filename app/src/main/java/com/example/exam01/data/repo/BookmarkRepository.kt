package com.example.exam01.data.repo

import com.example.exam01.room.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    val bookmarkList: Flow<List<BookmarkEntity>>

    suspend fun addBookmark(item: BookmarkEntity): Long

    suspend fun deleteBookmark(item: BookmarkEntity): Int
}