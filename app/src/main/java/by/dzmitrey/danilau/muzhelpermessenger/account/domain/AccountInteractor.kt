package by.dzmitrey.danilau.muzhelpermessenger.account.domain

import by.dzmitrey.danilau.muzhelpermessenger.account.data.repositories.AccountRepository
import by.dzmitrey.danilau.muzhelpermessenger.network.Result
import by.dzmitrey.danilau.muzhelpermessenger.network.responses.RegisterResponse

class AccountInteractor (
    private val repository: AccountRepository
) {
    fun register(email: String, password: String, token:String, registerDate: String): Result<RegisterResponse> {
        //TODO implement mapper for registerEntity
            return  repository.register(RegisterEntity(email,password,token,registerDate))
    }


}