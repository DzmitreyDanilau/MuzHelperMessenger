package by.dzmitrey.danilau.muzhelpermessenger.account.data.repositories

import by.dzmitrey.danilau.muzhelpermessenger.account.data.AccountCache
import by.dzmitrey.danilau.muzhelpermessenger.network.ApiService

class AccountRepository(
    private val service: ApiService,
    private val accountCache: AccountCache
)
