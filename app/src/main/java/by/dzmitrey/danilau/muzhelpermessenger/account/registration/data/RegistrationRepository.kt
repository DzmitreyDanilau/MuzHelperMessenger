package by.dzmitrey.danilau.muzhelpermessenger.account.registration.data

import by.dzmitrey.danilau.muzhelpermessenger.account.registration.domain.RegisterEntity
import by.dzmitrey.danilau.muzhelpermessenger.network.message
import by.dzmitrey.danilau.muzhelpermessenger.network.responses.ApiResponse
import by.dzmitrey.danilau.muzhelpermessenger.network.responses.RegisterResponse
import by.dzmitrey.danilau.muzhelpermessenger.utils.ErrorsConstants.Companion.ERROR_UNKNOWN
import by.dzmitrey.danilau.muzhelpermessenger.utils.State
import javax.inject.Inject

class RegistrationRepository @Inject constructor(private val remoteUserService: RemoteUserService) {

    fun register(registerEntity: RegisterEntity, request: (state: State<RegisterResponse?>) -> Unit) {
        remoteUserService.registerUser(registerEntity) { response ->
            when (response) {
                is ApiResponse.Success -> request(State.Success(response.data))
                is ApiResponse.Failure.Error -> request(State.Error(response.message()))
                else -> request(State.Exception(ERROR_UNKNOWN))
            }
        }
    }
}