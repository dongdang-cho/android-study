package com.example.exam01.util
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable? = null) : Result<Nothing>()

    data class NoData<T>(val data: T): Result<T>()
}
