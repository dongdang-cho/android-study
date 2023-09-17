package com.example.domain.exceptions


class EmptyBodyException(message: String? = "") : Exception(message)

class NetworkFailureException(message: String? = "") : Exception(message)

class SearchErrorException(message: String? = "") : Exception(message)