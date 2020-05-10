package by.dzmitrey.danilau.muzhelpermessenger.account.registration.data

sealed class RegistrationResult {

    class RegistrationPassed(string: String) : RegistrationResult()

    class RegistrationFailed(string: String) : RegistrationResult()

}