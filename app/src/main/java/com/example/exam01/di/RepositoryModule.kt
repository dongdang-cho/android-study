package com.example.movie.di

import com.example.exam01.data.repo.KakaoRepository
import com.example.exam01.data.repo.KakaoRepositoryImpl
import com.example.exam01.data.source.KakaoRemoteDataSource
import com.example.exam01.data.source.KakaoRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindBookRepository(kakaoRepositoryImpl: KakaoRepositoryImpl) : KakaoRepository

    @Binds
    abstract fun bindBookRemoteDataSource(KakaoRemoteDataSourceImpl: KakaoRemoteDataSourceImpl) : KakaoRemoteDataSource

}