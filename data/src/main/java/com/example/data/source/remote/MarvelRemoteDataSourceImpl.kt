package com.example.data.source.remote

import com.example.data.network.MarvelApi
import com.example.model.api.MarvelCharatersResponse
import retrofit2.Response
import javax.inject.Inject

class MarvelRemoteDataSourceImpl @Inject constructor(private val mavelApi: MarvelApi) :
    MarvelRemoteDataSource {

    override suspend fun searchCharacters(start: Int, limit: Int): Response<MarvelCharatersResponse> {
        return mavelApi.getCharacters(start = start, limit=limit)
    }
}