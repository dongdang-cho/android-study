package com.example.exam01.network.response

data class KakaoBooksResponse(
    val documents: List<Document>,
    val meta: Meta
)