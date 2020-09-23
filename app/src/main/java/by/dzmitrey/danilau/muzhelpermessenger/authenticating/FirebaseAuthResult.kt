package by.dzmitrey.danilau.muzhelpermessenger.authenticating

sealed class FirebaseAuthResult {
    object Success : FirebaseAuthResult()
    class Failed(error: String? = null) : FirebaseAuthResult()
}