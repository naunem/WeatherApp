package com.example.weatherapp.data.source

import java.io.IOException

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()

    data class GenericError(val code: Int, val message: String) : ResultWrapper<Nothing>()

    data class NetworkError(val io: IOException) : ResultWrapper<Nothing>()
}
