package by.dzmitrey.danilau.muzhelpermessenger.account.registration.presentation

import androidx.lifecycle.MutableLiveData
import by.dzmitrey.danilau.muzhelpermessenger.account.registration.data.RegistrationRepository
import by.dzmitrey.danilau.muzhelpermessenger.account.registration.domain.RegisterEntity
import by.dzmitrey.danilau.muzhelpermessenger.base.presentation.BaseViewModel
import by.dzmitrey.danilau.muzhelpermessenger.utils.State
import by.dzmitrey.danilau.muzhelpermessenger.utils.State.Exception
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(private val registerRepository: RegistrationRepository) : BaseViewModel() {

    val data: MutableLiveData<UIState> = MutableLiveData()

    fun register(registerEntity: RegisterEntity) {
        registerRepository.register(registerEntity) {
            when (it) {
                is State.Success -> {
                    data.postValue(UIState.Data)
                }
                is Error -> {
                    data.postValue(UIState.Error(it.message))
                }
                is Exception -> {
                    data.postValue(UIState.Error(it.message))
                }
                else -> {
                    data.postValue(UIState.Progress)
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