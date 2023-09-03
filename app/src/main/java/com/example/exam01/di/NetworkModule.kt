package com.example.movie.di

import com.example.exam01.constant.KakaoConstants
import com.example.exam01.network.KakaoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideKakaoRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(KakaoConstants.BASE_BOOK_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideKakaoApi(retrofit: Retrofit): KakaoApi =
        retrofit.create(KakaoApi::class.java)


}