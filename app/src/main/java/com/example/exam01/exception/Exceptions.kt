package com.example.exam01.exception


class EmptyBodyException(message: String? = "") : Exception(message)

class NetworkFailureException(message: String? = "") : Exception(message)

class SearchErrorException(message: String? = "") : Exception(message)