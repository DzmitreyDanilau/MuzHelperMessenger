package by.dzmitrey.danilau.muzhelpermessenger.utils

import by.dzmitrey.danilau.muzhelpermessenger.network.Result

interface Mapper<out T : Any> {
    fun mapToResult(): Result<T>
}