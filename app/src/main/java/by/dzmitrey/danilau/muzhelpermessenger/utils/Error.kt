package by.dzmitrey.danilau.muzhelpermessenger.utils

sealed class Error {

    object NetworkError : Error()

    object GenericError : Error()

    object ResponseError : Error()

    object PersistenceError : Error()
}