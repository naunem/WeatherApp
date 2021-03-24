package com.example.weatherapp.data.source.remote.network

import com.example.weatherapp.data.source.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

abstract class SafeApi {
    suspend fun <R> safeApiCall(
        callFunction: suspend () -> R
    ): ResultWrapper<R> {
        return withContext(Dispatchers.IO) {
            try {
                ResultWrapper.Success(callFunction())
            } catch (e: IOException) {
                ResultWrapper.NetworkError(e)
            } catch (e: HttpException) {
                ResultWrapper.GenericError(e.code(), e.message())
            }
        }
    }
}
