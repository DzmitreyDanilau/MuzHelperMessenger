package by.dzmitrey.danilau.muzhelpermessenger.authenticating

sealed class FirebaseAuthResult {
    object Success : FirebaseAuthResult()
    class Failed(val error: String? = null) : FirebaseAuthResult()
}