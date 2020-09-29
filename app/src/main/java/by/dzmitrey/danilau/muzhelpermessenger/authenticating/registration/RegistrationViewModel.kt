package by.dzmitrey.danilau.muzhelpermessenger.authenticating.registration

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.CredentialsEntity
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.FirebaseAuthResult
import by.dzmitrey.danilau.muzhelpermessenger.base.presentation.BaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(private val registrationRepository: RegistrationRepository) : BaseViewModel() {

    private var googleSignInClient: MutableLiveData<GoogleSignInClient> = MutableLiveData()

    private var firebaseAuthResult: MutableLiveData<FirebaseAuthResult> = MutableLiveData()

    fun getGoogleSignInOptions(): LiveData<GoogleSignInClient> = googleSignInClient

    fun getFirebaseAuthResult(): LiveData<FirebaseAuthResult> = firebaseAuthResult

    fun googleAuthForFirebase(data: Intent?) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val user = registrationRepository.googleAuthForFirebase(data)
                user?.let {
                    firebaseAuthResult.postValue(FirebaseAuthResult.Success)
                }
            } catch (exception: Exception) {
                firebaseAuthResult.postValue(FirebaseAuthResult.Failed(exception.message))
            }
        }
    }

    fun registerWithEmailAndPassword(credentials: CredentialsEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val user = registrationRepository.authWithEmail(credentials)
                user?.let {
                    firebaseAuthResult.postValue(FirebaseAuthResult.Success)
                }
            } catch (exception: Exception) {
                firebaseAuthResult.postValue(FirebaseAuthResult.Failed(exception.message))
            }
        }
    }

    fun getGoogleSignInClient() {
        googleSignInClient.value = registrationRepository.getGoogleSignInClient()
    }

    fun getCurrentUser(): FirebaseUser? {
        return registrationRepository.getCurrentUser()
    }
}