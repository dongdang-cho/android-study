package com.example.data.repo

import com.example.model.api.MarvelCharatersResponse
import retrofit2.Response

interface MarvelRepository {
    suspend fun searchCharacters(
        start: Int, limit: Int
    ) : Response<MarvelCharatersResponse>
}