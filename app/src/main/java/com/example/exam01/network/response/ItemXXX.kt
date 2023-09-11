package com.example.exam01.network.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemXXX(
    val name: String,
    val resourceURI: String,
    val type: String
) : Parcelable