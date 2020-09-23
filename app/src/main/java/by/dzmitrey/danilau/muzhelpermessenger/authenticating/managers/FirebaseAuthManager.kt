package by.dzmitrey.danilau.muzhelpermessenger.authenticating.managers

import android.content.Context
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.CredentialsEntity
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@Suppress("JAVA_CLASS_ON_COMPANION")
class FirebaseAuthManager @Inject constructor(private val context: Context) {

    companion object {
        private val TAG = FirebaseAuthManager.javaClass.name
    }

    private val firebaseAuth = Firebase.auth

    suspend fun googleAuthForFirebase(credentials: AuthCredential?): FirebaseUser? {
        return credentials?.let {
            authWithGoogle(credentials)
        }
    }

    suspend fun registerWithEmailAndPassword(credentials: CredentialsEntity): FirebaseUser? {
        return authWithEmailAndPassword(credentials)
    }

    private suspend fun authWithGoogle(credentials: AuthCredential): FirebaseUser? {
        firebaseAuth.signInWithCredential(credentials).await()
        return firebaseAuth.currentUser ?: throw FirebaseAuthException("", "")
    }

    private suspend fun authWithEmailAndPassword(credentials: CredentialsEntity): FirebaseUser? {
        firebaseAuth.createUserWithEmailAndPassword(credentials.email, credentials.password).await()
        return firebaseAuth.currentUser ?: throw FirebaseAuthException("", "")
    }
}