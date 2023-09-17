package com.example.exam01.usecase

import android.util.Log
import com.example.exam01.data.repo.MarvelRepository
import com.example.exam01.exception.EmptyBodyException
import com.example.exam01.exception.NetworkFailureException
import com.example.exam01.exception.SearchErrorException
import com.example.exam01.network.response.Data
import com.example.exam01.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val marvelRepository: MarvelRepository,
) {
    operator fun invoke(
        start: Int, size: Int = DEFAULT_LIMIT
    ): Flow<Result<SearchUiState>> = flow<Result<SearchUiState>> {
        val response = marvelRepository.searchCharacters(start, size)
        if (response.isSuccessful) {
            response.body()?.let {
                if (it.data.isLast()) {
                    emit(Result.Success(SearchUiState.End))
                } else {
                    val data: Data =
                        response.body()?.data
                            ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")
                    emit(Result.Success(SearchUiState.GetData(data)))
                }
            } ?: throw SearchErrorException("[${response.code()}] - ${response.raw()}")
        } else {
            throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
        }
    }.catch {
        emit(Result.Error(it))
    }
    companion object {
        private const val DEFAULT_LIMIT = 20
    }
}

sealed interface SearchUiState {
    data class GetData(val data: Data) : SearchUiState
    object End : SearchUiState
}