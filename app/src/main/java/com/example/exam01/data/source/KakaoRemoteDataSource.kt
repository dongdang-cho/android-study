package com.example.exam01.data.source

import com.example.exam01.network.response.KakaoBooksResponse
import retrofit2.Response

interface KakaoRemoteDataSource {
    suspend fun searchBooks(
        query: String, start: Int, size: Int
    ): Response<KakaoBooksResponse>
}