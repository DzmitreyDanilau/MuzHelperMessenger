package by.dzmitrey.danilau.muzhelpermessenger.account.registration.data

import by.dzmitrey.danilau.muzhelpermessenger.account.registration.domain.RegisterEntity
import by.dzmitrey.danilau.muzhelpermessenger.network.message
import by.dzmitrey.danilau.muzhelpermessenger.network.responses.ApiResponse
import by.dzmitrey.danilau.muzhelpermessenger.network.responses.RegisterResponse
import by.dzmitrey.danilau.muzhelpermessenger.utils.Result
import javax.inject.Inject

class RegistrationRepository @Inject constructor(private val remoteUserService: RemoteUserService) {

    fun register(registerEntity: RegisterEntity) : Result<RegisterResponse>? {
        val result : Result<RegisterResponse>? = null
        remoteUserService.registerUser { response ->
             when (response) {
                is ApiResponse.Success -> Result.Success(response)
                is ApiResponse.Failure.Error -> Result.Error(response.message())
                else -> Result.Exception(response)
            }
        }
        return result
    }
}