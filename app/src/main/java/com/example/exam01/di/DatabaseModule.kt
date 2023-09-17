package com.example.exam01.di

import android.content.Context
import androidx.room.Room
import com.example.exam01.room.BookmarkDao
import com.example.exam01.room.BookmarkDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun databaseModule(@ApplicationContext context: Context): BookmarkDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            BookmarkDatabase::class.java,
            "bookmark"
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun daoModule(database: BookmarkDatabase): BookmarkDao =
        database.bookmarkDao()
}