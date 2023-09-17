package com.example.data.source.remote

import com.example.model.api.MarvelCharatersResponse
import retrofit2.Response

interface MarvelRemoteDataSource {
    suspend fun searchCharacters(
        start: Int, limit: Int
    ): Response<MarvelCharatersResponse>
}