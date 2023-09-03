package com.example.exam01.data.repo

import com.example.exam01.data.source.KakaoRemoteDataSource
import com.example.exam01.network.response.KakaoBooksResponse
import retrofit2.Response
import javax.inject.Inject

class KakaoRepositoryImpl @Inject constructor(private val kakaoRemoteDataSource: KakaoRemoteDataSource) :
    KakaoRepository {

    override suspend fun searchBooks (
        query: String, start: Int, size: Int
    ): Response<KakaoBooksResponse> =
        kakaoRemoteDataSource.searchBooks(query = query, start, size)
}