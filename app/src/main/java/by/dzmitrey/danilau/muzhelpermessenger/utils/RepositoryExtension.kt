package by.dzmitrey.danilau.muzhelpermessenger.utils

import by.dzmitrey.danilau.muzhelpermessenger.utils.Constants.Companion.NETWORK_TIMEOUT
import by.dzmitrey.danilau.muzhelpermessenger.utils.ErrorsConstants.Companion.ERROR_UNKNOWN
import by.dzmitrey.danilau.muzhelpermessenger.utils.ErrorsConstants.Companion.TIME_OUT_EXCEPTION_CODE
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import java.io.IOException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): ApiResponse<T> {
    try {
        withTimeout(NETWORK_TIMEOUT) {
            ApiResponse.Success(apiCall.invoke())
        }
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> ApiResponse.NetworkError
            is TimeoutCancellationException -> ApiResponse.GenericError(TIME_OUT_EXCEPTION_CODE, throwable.message)
            else -> ApiResponse.GenericError(error = ERROR_UNKNOWN)
        }
    }
}