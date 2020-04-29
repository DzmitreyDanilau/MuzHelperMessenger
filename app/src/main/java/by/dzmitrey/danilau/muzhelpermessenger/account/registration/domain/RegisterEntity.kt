package by.dzmitrey.danilau.muzhelpermessenger.account.registration.domain

class RegisterEntity(
    val name: String,
    val email: String,
    val password: String,
    val token: String,
    val status: String = "",
    val image: String = "",
    //Temporary it is 0
    val registrationDate: Int = 0,
    val lastSeenTime: Int = 0
)