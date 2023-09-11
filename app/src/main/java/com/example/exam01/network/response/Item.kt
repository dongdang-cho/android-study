package com.example.exam01.network.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val name: String,
    val resourceURI: String
) : Parcelable