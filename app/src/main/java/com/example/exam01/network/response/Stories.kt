package com.example.exam01.network.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXX>,
    val returned: Int
) : Parcelable