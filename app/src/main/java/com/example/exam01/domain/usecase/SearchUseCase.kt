package com.example.exam01.domain.usecase

import com.example.exam01.data.repo.KakaoRepository
import com.example.exam01.network.response.KakaoBooksResponse
import com.example.exam01.util.Result
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val kakaoRepository: KakaoRepository) {

    suspend operator fun invoke(
        query: String, start: Int, size: Int
    ): Result<KakaoBooksResponse> {
        val response = kakaoRepository.searchBooks(query, start, size)
        if (response.isSuccessful) {
            response.body()?.let {
                if (it.meta.total_count == 0) {
                    return Result.NoData(it)
                } else {
                    return Result.Success(it)
                }
            } ?: kotlin.run {
                return Result.Error(Exception("검색을 실패하였습니다."))
            }
        } else {
            return Result.Error(Exception("검색을 실패하였습니다."))
        }
    }
}