package com.example.data.repo

import com.example.data.source.remote.MarvelRemoteDataSource
import com.example.model.api.MarvelCharatersResponse
import retrofit2.Response
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(private val marvelRemoteDataSource: MarvelRemoteDataSource) : MarvelRepository{

    override suspend fun searchCharacters(start: Int, limit: Int): Response<MarvelCharatersResponse> =
        marvelRemoteDataSource.searchCharacters(start = start, limit=limit)
}