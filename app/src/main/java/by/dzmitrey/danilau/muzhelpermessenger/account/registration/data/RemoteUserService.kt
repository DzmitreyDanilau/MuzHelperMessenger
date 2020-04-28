package by.dzmitrey.danilau.muzhelpermessenger.account.registration.data

import by.dzmitrey.danilau.muzhelpermessenger.network.ApiService
import by.dzmitrey.danilau.muzhelpermessenger.network.responses.ApiResponse
import by.dzmitrey.danilau.muzhelpermessenger.network.responses.RegisterResponse
import by.dzmitrey.danilau.muzhelpermessenger.network.transform
import javax.inject.Inject

class RemoteUserService @Inject constructor(private val apiService: ApiService) {

    fun registerUser(onResult: (response: ApiResponse<RegisterResponse>) -> Unit) {
        apiService.register().transform(onResult)
    }
}