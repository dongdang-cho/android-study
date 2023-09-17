package com.example.exam01.data.source.local

import com.example.exam01.room.BookmarkDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkLocalDataSourceImpl  @Inject constructor(private val bookmarkDao: BookmarkDao) :
    BookmarkLocalDataSource {
    override val bookmarkList: Flow<List<BookmarkEntity>>
        get() = bookmarkDao.getBookmarkList()

    override suspend fun addBookmark(item: BookmarkEntity): Long =
        bookmarkDao.insertBookmark(item)

    override suspend fun deleteBookmark(item: BookmarkEntity): Int =
        bookmarkDao.deleteBookmark(item)
}