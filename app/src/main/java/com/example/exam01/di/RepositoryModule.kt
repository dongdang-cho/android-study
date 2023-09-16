package com.example.exam01.di

import com.example.exam01.data.repo.BookmarkRepository
import com.example.exam01.data.repo.BookmarkRepositoryImpl
import com.example.exam01.data.repo.MarvelRepository
import com.example.exam01.data.repo.MarvelRepositoryImpl
import com.example.exam01.data.source.local.BookmarkLocalDataSource
import com.example.exam01.data.source.local.BookmarkLocalDataSourceImpl
import com.example.exam01.data.source.remote.MarvelRemoteDataSource
import com.example.exam01.data.source.remote.MarvelRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindMarvelRepository(marvelRepositoryImpl: MarvelRepositoryImpl) : MarvelRepository

    @Binds
    abstract fun bindBookRemoteDataSource(marvelRemoteDataSourceImpl: MarvelRemoteDataSourceImpl) : MarvelRemoteDataSource


    @Binds
    abstract fun bindBookLocalDataSource(bookmarkLocalDataSourceImpl: BookmarkLocalDataSourceImpl): BookmarkLocalDataSource

    @Binds
    abstract fun bindBookmarkRepository(bookmarkRepositoryImpl: BookmarkRepositoryImpl): BookmarkRepository

}