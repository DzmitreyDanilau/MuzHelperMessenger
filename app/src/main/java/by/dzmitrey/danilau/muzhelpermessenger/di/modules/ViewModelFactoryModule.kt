package by.dzmitrey.danilau.muzhelpermessenger.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.login.LoginViewModel
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.registration.RegistrationViewModel
import by.dzmitrey.danilau.muzhelpermessenger.di.ViewModelKey
import by.dzmitrey.danilau.muzhelpermessenger.home.HomeViewModel
import by.dzmitrey.danilau.muzhelpermessenger.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelFactoryModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(factory: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(factory: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    fun bindRegistrationViewModel(factory: RegistrationViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}