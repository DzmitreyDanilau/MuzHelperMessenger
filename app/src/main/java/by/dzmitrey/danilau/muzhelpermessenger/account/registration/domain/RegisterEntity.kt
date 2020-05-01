package by.dzmitrey.danilau.muzhelpermessenger.account.registration.domain

import by.dzmitrey.danilau.muzhelpermessenger.utils.extensions.Empty

class RegisterEntity(
    val name: String,
    val email: String,
    val password: String,
    val token: String = String.Empty(),
    val status: String = String.Empty(),
    val image: String =String.Empty(),
    //Temporary it is 0
    val registrationDate: Int = 0,
    val lastSeenTime: Int = 0
)