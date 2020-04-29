package by.dzmitrey.danilau.muzhelpermessenger.account.registration.data

import by.dzmitrey.danilau.muzhelpermessenger.account.registration.domain.RegisterEntity
import by.dzmitrey.danilau.muzhelpermessenger.network.ApiService
import by.dzmitrey.danilau.muzhelpermessenger.network.responses.ApiResponse
import by.dzmitrey.danilau.muzhelpermessenger.network.responses.RegisterResponse
import by.dzmitrey.danilau.muzhelpermessenger.network.transform
import javax.inject.Inject

class RemoteUserService @Inject constructor(private val apiService: ApiService) {

    companion object {
        const val PARAM_EMAIL = "email"
        const val PARAM_PASSWORD = "password"
        const val PARAM_NAME = "name"
        const val PARAM_TOKEN = "token"
        const val PARAM_STATUS = "status"
        const val PARAM_REGISTRATION_DATE = "user_date"
        const val PARAM_IMAGE = "image"
        const val PARAM_LAST_SEEN = "last_seen"
    }

    fun registerUser(registerEntity: RegisterEntity, onResult: (response: ApiResponse<RegisterResponse>) -> Unit) {
        apiService.register(createRegisterMap(registerEntity)).transform(onResult)
    }

    private fun createRegisterMap(registerEntity: RegisterEntity): Map<String, String> {
        val map = HashMap<String, String>()
        map[PARAM_NAME] = registerEntity.name
        map[PARAM_EMAIL] = registerEntity.email
        map[PARAM_PASSWORD] = registerEntity.password
        map[PARAM_TOKEN] = registerEntity.token
        map[PARAM_STATUS] = registerEntity.status
        map[PARAM_IMAGE] = registerEntity.image
        map[PARAM_REGISTRATION_DATE] = registerEntity.registrationDate.toString()
        map[PARAM_LAST_SEEN] = registerEntity.lastSeenTime.toString()
        return map
    }
}