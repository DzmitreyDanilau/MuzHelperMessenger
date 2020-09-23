package by.dzmitrey.danilau.muzhelpermessenger.base.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    private val error: MutableLiveData<Throwable> = MutableLiveData()

    fun getError(): LiveData<Throwable> = error
}

