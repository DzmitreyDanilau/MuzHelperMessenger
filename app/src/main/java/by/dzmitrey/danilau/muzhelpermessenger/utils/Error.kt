package by.dzmitrey.danilau.muzhelpermessenger.utils

sealed class Error(val message: String? = null) {

    class NetworkError(message: String) : Error()

    class GenericError(message: String) : Error()

    class ResponseError(message: String) : Error()

    class PersistenceError(message: String) : Error()

}