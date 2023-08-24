package com.example.exam01.data.repo

import com.example.exam01.data.source.MarvelRemoteDataSource
import com.example.exam01.network.response.MarvelCharatersResponse
import retrofit2.Response
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(private val marvelRemoteDataSource: MarvelRemoteDataSource) : MarvelRepository{

    override suspend fun searchCharacters(start: Int, limit: Int): Response<MarvelCharatersResponse> =
        marvelRemoteDataSource.searchCharacters(start = start, limit=limit)
}