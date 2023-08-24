package com.example.exam01.data.repo

import com.example.exam01.network.response.MarvelCharatersResponse
import retrofit2.Response

interface MarvelRepository {
    suspend fun searchCharacters(
        start: Int, limit: Int
    ) : Response<MarvelCharatersResponse>
}