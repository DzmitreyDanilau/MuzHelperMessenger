package by.dzmitrey.danilau.muzhelpermessenger.base.data.network

sealed class ApiResponse<out T> {

    data class Success<out T>(val value: T) : ApiResponse<T>()

    data class GenericError(val code: Int? = null, val errorMessage: String? = null) : ApiResponse<Nothing>()

    object NetworkError : ApiResponse<Nothing>()
}