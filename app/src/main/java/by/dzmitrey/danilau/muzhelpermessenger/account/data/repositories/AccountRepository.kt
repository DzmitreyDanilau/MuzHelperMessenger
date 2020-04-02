package by.dzmitrey.danilau.muzhelpermessenger.account.data.repositories

import by.dzmitrey.danilau.muzhelpermessenger.account.data.AccountCache
import by.dzmitrey.danilau.muzhelpermessenger.account.domain.RegisterEntity
import by.dzmitrey.danilau.muzhelpermessenger.network.ApiService
import by.dzmitrey.danilau.muzhelpermessenger.network.responses.RegisterResponse

class AccountRepository (
    private val service: ApiService,
    private val accountCache: AccountCache
) {
    fun register(registerEntity: RegisterEntity): Result<RegisterResponse> {
        return  service.register(registerEntity)
    }
}