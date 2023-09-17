package com.example.domain.usecase

import com.example.data.repo.MarvelRepository
import com.example.domain.exceptions.EmptyBodyException
import com.example.domain.exceptions.NetworkFailureException
import com.example.domain.exceptions.SearchErrorException
import com.example.model.api.Data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.example.domain.util.Result

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