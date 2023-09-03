package com.example.exam01.data.source

import com.example.exam01.network.KakaoApi
import com.example.exam01.network.response.KakaoBooksResponse
import retrofit2.Response
import javax.inject.Inject

class KakaoRemoteDataSourceImpl @Inject constructor(private val kakaoApi: KakaoApi) :
    KakaoRemoteDataSource {

    override suspend fun searchBooks(
        query: String, start: Int, size: Int
    ): Response<KakaoBooksResponse> =
        kakaoApi.getBookList(query = query, size = size, page = start)

}