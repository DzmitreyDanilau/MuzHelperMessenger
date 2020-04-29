package by.dzmitrey.danilau.muzhelpermessenger.utils

sealed class State<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : State<T>(data)
    class Loading<T>(data: T? = null) : State<T>(data)
    class Error<T>(message: String, data: T? = null) : State<T>(data, message)
    class Exception<T>(message: String? = null, data: T? = null) : State<T>(data, message)

}
