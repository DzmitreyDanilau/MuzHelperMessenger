package by.dzmitrey.danilau.muzhelpermessenger.network

import by.dzmitrey.danilau.muzhelpermessenger.account.domain.RegisterEntity
import by.dzmitrey.danilau.muzhelpermessenger.base.data.network.ApiResult
import by.dzmitrey.danilau.muzhelpermessenger.network.responses.RegisterResponse
import retrofit2.http.POST

interface ApiService {

    @POST
    fun register(registerEntity: RegisterEntity): ApiResult<RegisterResponse>

}