package by.dzmitrey.danilau.muzhelpermessenger.authenticating.login

import by.dzmitrey.danilau.muzhelpermessenger.authenticating.CredentialsEntity
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.managers.FirebaseAuthManager
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class LoginRepository @Inject constructor(private val firebaseAuthManager: FirebaseAuthManager) {

    suspend fun logInWithEmailAndPassword(credentials: CredentialsEntity): FirebaseUser? {
        return firebaseAuthManager.loginInWithEmailAndPassword(credentials)
    }
}