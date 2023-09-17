package com.example.data.network

import com.example.data.constants.MarvelConstants.API_KEY
import com.example.data.constants.MarvelConstants.hash
import com.example.data.constants.MarvelConstants.ts
import com.example.model.api.MarvelCharatersResponse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("v1/public/characters?apikey=$API_KEY&ts=$ts&hash=$hash")
    suspend fun getCharacters(
        @Query("offset") start: Int,
        @Query("limit") limit: Int
    ): Response<MarvelCharatersResponse>
}
