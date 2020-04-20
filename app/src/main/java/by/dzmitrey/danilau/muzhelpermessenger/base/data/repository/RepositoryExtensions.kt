package by.dzmitrey.danilau.muzhelpermessenger.base.data.repository

import by.dzmitrey.danilau.muzhelpermessenger.base.data.CacheResult
import by.dzmitrey.danilau.muzhelpermessenger.base.data.network.ApiResponse
import by.dzmitrey.danilau.muzhelpermessenger.network.responses.ErrorResponse
import by.dzmitrey.danilau.muzhelpermessenger.utils.Constants.Companion.CACHE_TIMEOUT
import by.dzmitrey.danilau.muzhelpermessenger.utils.Constants.Companion.NETWORK_TIMEOUT
import by.dzmitrey.danilau.muzhelpermessenger.utils.Constants.Companion.TIME_OUT_ERROR_CODE
import by.dzmitrey.danilau.muzhelpermessenger.utils.ErrorsConstants.Companion.CACHE_ERROR_TIMEOUT
import by.dzmitrey.danilau.muzhelpermessenger.utils.ErrorsConstants.Companion.ERROR_UNKNOWN
import by.dzmitrey.danilau.muzhelpermessenger.utils.ErrorsConstants.Companion.NETWORK_ERROR_TIMEOUT
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): ApiResponse<T> {
    return withContext(dispatcher) {
        withTimeout(NETWORK_TIMEOUT) {
            try {
                ApiResponse.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is TimeoutCancellationException -> ApiResponse.GenericError(TIME_OUT_ERROR_CODE, NETWORK_ERROR_TIMEOUT)
                    is IOException -> ApiResponse.NetworkError
                    is HttpException -> {
                        val errorCode = throwable.code()
                        val errorResponse = convertErrorBody(throwable)
                        ApiResponse.GenericError(errorCode, errorResponse?.message)
                    }
                    else -> ApiResponse.GenericError(null, ERROR_UNKNOWN)
                }
            }
        }
    }
}

suspend fun <T> safeCacheCall(dispatcher: CoroutineDispatcher, cacheCall: suspend () -> T): CacheResult<T> {
    return withContext(dispatcher) {
        try {
            withTimeout(CACHE_TIMEOUT) {
                CacheResult.Success(cacheCall.invoke())
            }
        } catch (throwable: Throwable) {
            when (throwable) {
                is TimeoutCancellationException -> CacheResult.GenericError(CACHE_ERROR_TIMEOUT)
                else -> CacheResult.GenericError(ERROR_UNKNOWN)
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.source()?.let {
            val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
            moshiAdapter.fromJson(it)
        }
    } catch (exception: Exception) {
        null
    }
}