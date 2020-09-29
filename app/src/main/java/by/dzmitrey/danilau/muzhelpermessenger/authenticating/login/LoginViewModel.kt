package by.dzmitrey.danilau.muzhelpermessenger.authenticating.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.CredentialsEntity
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.FirebaseAuthResult
import by.dzmitrey.danilau.muzhelpermessenger.base.presentation.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : BaseViewModel() {

    private val loginResult: MutableLiveData<FirebaseAuthResult> = MutableLiveData()

    fun getLoginResult(): LiveData<FirebaseAuthResult> = loginResult

    fun loginWithEmailAndPassword(credentials: CredentialsEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val user = loginRepository.logInWithEmailAndPassword(credentials)
                user?.let {
                    loginResult.postValue(FirebaseAuthResult.Success)
                }
            } catch (exception: Exception) {
                loginResult.postValue(FirebaseAuthResult.Failed(exception.message))
            }
        }
    }
}