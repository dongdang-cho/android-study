package com.example.exam01.network.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
) : Parcelable {

    fun isLast(): Boolean = offset >= total

}