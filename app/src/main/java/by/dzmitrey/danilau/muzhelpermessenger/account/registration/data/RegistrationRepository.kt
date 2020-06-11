package by.dzmitrey.danilau.muzhelpermessenger.account.registration.data

import by.dzmitrey.danilau.muzhelpermessenger.account.registration.domain.RegisterEntity
import by.dzmitrey.danilau.muzhelpermessenger.utils.ApiResponse
import by.dzmitrey.danilau.muzhelpermessenger.utils.safeApiCall
import javax.inject.Inject

class RegistrationRepository @Inject constructor(private val remoteUserService: RemoteUserService) {
    suspend fun register(registerEntity: RegisterEntity): ApiResponse<RegistrationResult> {
        return safeApiCall {
            remoteUserService.registerUser(registerEntity)
        }
    }
}