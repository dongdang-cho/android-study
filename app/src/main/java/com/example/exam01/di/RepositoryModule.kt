package com.example.exam01.di

import com.example.exam01.data.repo.BookmarkRepository
import com.example.exam01.data.repo.BookmarkRepositoryImpl
import com.example.exam01.data.source.local.BookmarkLocalDataSource
import com.example.exam01.data.source.local.BookmarkLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindBookLocalDataSource(bookmarkLocalDataSourceImpl: BookmarkLocalDataSourceImpl): BookmarkLocalDataSource

    @Binds
    abstract fun bindBookmarkRepository(bookmarkRepositoryImpl: BookmarkRepositoryImpl): BookmarkRepository

}