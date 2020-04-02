package by.dzmitrey.danilau.muzhelpermessenger.registration.domain

import by.dzmitrey.danilau.muzhelpermessenger.account.domain.RegisterEntity
import by.dzmitrey.danilau.muzhelpermessenger.base.domain.BaseUseCase
import javax.inject.Inject

class RegistrationInteractor @Inject constructor() : BaseUseCase<RegisterEntity>() {

    override suspend fun run(params: RegisterEntity) {
        TODO("Not yet implemented")
    }
}