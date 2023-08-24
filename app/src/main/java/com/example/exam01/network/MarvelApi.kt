package com.example.exam01.network

import com.example.exam01.constant.MarvelConstants.API_KEY
import com.example.exam01.constant.MarvelConstants.hash
import com.example.exam01.constant.MarvelConstants.ts
import com.example.exam01.network.response.MarvelCharatersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("v1/public/characters?apikey=$API_KEY&ts=$ts&hash=$hash")
    suspend fun getCharacters(@Query("offset") start: Int, @Query("limit") limit: Int) : Response<MarvelCharatersResponse>
}
