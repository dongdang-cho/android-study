package com.example.exam01.data.source.remote

import com.example.exam01.network.response.MarvelCharatersResponse
import retrofit2.Response

interface MarvelRemoteDataSource {
    suspend fun searchCharacters(
        start: Int, limit: Int
    ): Response<MarvelCharatersResponse>
}