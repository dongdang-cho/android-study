package com.example.exam01.data.source.remote

import android.util.Log
import com.example.exam01.network.MarvelApi
import com.example.exam01.network.response.MarvelCharatersResponse
import retrofit2.Response
import javax.inject.Inject

class MarvelRemoteDataSourceImpl @Inject constructor(private val mavelApi: MarvelApi) :
    MarvelRemoteDataSource {


    override suspend fun searchCharacters(start: Int, limit: Int): Response<MarvelCharatersResponse> {
        Log.i("실행", "실행!!!!!!!!!!!!")
        return mavelApi.getCharacters(start = start, limit=limit)

    }


}