package com.example.exam01.network

import com.example.exam01.constant.KakaoConstants.API_KEY
import com.example.exam01.network.response.KakaoBooksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoApi {
    @Headers("Authorization: KakaoAK ${API_KEY}")
    @GET("v3/search/book")
    suspend fun getBookList(
        @Query("query") query: String,
        @Query("size") size: Int,
        @Query("page") page: Int
    ): Response<KakaoBooksResponse>
}
