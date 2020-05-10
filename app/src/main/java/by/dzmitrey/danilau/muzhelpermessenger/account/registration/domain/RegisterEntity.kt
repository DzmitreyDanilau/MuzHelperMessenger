package by.dzmitrey.danilau.muzhelpermessenger.account.registration.domain

import by.dzmitrey.danilau.muzhelpermessenger.utils.extensions.EMPTY

class RegisterEntity(
    val name: String,
    val email: String,
    val password: String,
    val token: String = String.EMPTY,
    val status: String = String.EMPTY,
    val image: String = String.EMPTY,
    //Temporary it is 0
    val registrationDate: Int = 0,
    val lastSeenTime: Int = 0
)