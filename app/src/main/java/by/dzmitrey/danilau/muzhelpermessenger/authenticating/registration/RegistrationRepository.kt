package by.dzmitrey.danilau.muzhelpermessenger.authenticating.registration

import android.content.Intent
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.CredentialsEntity
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.managers.FirebaseAuthManager
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.managers.GoogleSignInManager
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class RegistrationRepository @Inject constructor(
    private val googleSignManager: GoogleSignInManager,
    private val firebaseAuthManager: FirebaseAuthManager
) {

    suspend fun googleAuthForFirebase(data: Intent?): FirebaseUser? {
        val client = googleSignManager.getGoogleCredentials(data)
        return firebaseAuthManager.googleAuthForFirebase(client)
    }

    suspend fun authWithEmail(credentials: CredentialsEntity): FirebaseUser? {
        return firebaseAuthManager.registerWithEmailAndPassword(credentials)
    }

    fun getGoogleSignInClient() = googleSignManager.configureSignInClient()
}