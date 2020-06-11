package by.dzmitrey.danilau.muzhelpermessenger.utils

sealed class ApiResponse<out T> {

    data class Success<out T>(val value: T) : ApiResponse<T>()

    data class GenericError(val errorCode: Int = 0, val error: String?) : ApiResponse<Nothing>()

    object NetworkError : ApiResponse<Nothing>()
}