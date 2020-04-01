package by.dzmitrey.danilau.muzhelpermessenger.utils

import by.dzmitrey.danilau.muzhelpermessenger.base.domain.network.Result

interface Mapper<out T : Any> {
    fun mapToResult(): Result<T>
}