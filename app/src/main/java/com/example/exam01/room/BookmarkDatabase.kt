package com.example.exam01.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [BookmarkEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(com.example.exam01.util.TypeConverter::class)
abstract class BookmarkDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}