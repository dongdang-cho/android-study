package com.example.exam01.data.repo

import com.example.exam01.data.source.local.BookmarkLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(private val bookmarkLocalDataSource: BookmarkLocalDataSource) : BookmarkRepository{
    override val bookmarkList: Flow<List<BookmarkEntity>>
        get() = bookmarkLocalDataSource.bookmarkList

    override suspend fun addBookmark(item: BookmarkEntity): Long =
        bookmarkLocalDataSource.addBookmark(item)

    override suspend fun deleteBookmark(item: BookmarkEntity): Int =
        bookmarkLocalDataSource.deleteBookmark(item)
}