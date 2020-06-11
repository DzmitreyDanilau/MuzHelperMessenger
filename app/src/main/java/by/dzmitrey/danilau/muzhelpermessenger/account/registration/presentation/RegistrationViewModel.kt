package by.dzmitrey.danilau.muzhelpermessenger.account.registration.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.dzmitrey.danilau.muzhelpermessenger.account.registration.data.RegistrationRepository
import by.dzmitrey.danilau.muzhelpermessenger.account.registration.domain.RegisterEntity
import by.dzmitrey.danilau.muzhelpermessenger.base.presentation.BaseViewModel
import by.dzmitrey.danilau.muzhelpermessenger.utils.ApiResponse
import by.dzmitrey.danilau.muzhelpermessenger.utils.State
import by.dzmitrey.danilau.muzhelpermessenger.utils.State.Exception
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(private val registerRepository: RegistrationRepository) : BaseViewModel() {

    private val isRegistrationFinished by lazy { MutableLiveData<UIState>() }

    fun getIsRegistrationFinished(): LiveData<UIState> = isRegistrationFinished

    fun register(registerEntity: RegisterEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                registerRepository.register(registerEntity) {
                    when (it) {
                        is ApiResponse.Success -> {
                            isRegistrationFinished.postValue(UIState.Data)
                        }
                        is Error -> {
                            isRegistrationFinished.postValue(UIState.Error(it.message))
                        }
                        is Exception -> {
                            isRegistrationFinished.postValue(UIState.Error(it.message))
                        }
                        else -> {
                            isRegistrationFinished.postValue(UIState.Progress)
                        }
                    }
                }
            }
        }
    }

    sealed class UIState {
        object Data : UIState()
        data class Error(val error: String?) : UIState()
        object Progress : UIState()
    }
}