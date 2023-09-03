package com.example.exam01.data.repo

import com.example.exam01.network.response.KakaoBooksResponse
import retrofit2.Response

interface KakaoRepository {
    suspend fun searchBooks(
        query: String, start: Int, size: Int
    ) : Response<KakaoBooksResponse>
}