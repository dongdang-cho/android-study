package com.example.exam01.data.source.local

import com.example.exam01.room.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface BookmarkLocalDataSource {
    val bookmarkList: Flow<List<BookmarkEntity>>

    suspend fun addBookmark(item: BookmarkEntity): Long

    suspend fun deleteBookmark(item: BookmarkEntity): Int
}