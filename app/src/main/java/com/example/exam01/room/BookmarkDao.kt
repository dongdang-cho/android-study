package com.example.exam01.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(item: BookmarkEntity): Long

    @Delete
    suspend fun deleteBookmark(item: BookmarkEntity): Int

    @Query("SELECT * FROM bookmark")
    fun getBookmarkList(): Flow<List<BookmarkEntity>>
}