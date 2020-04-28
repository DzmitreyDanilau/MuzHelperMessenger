package by.dzmitrey.danilau.muzhelpermessenger.di.modules

import androidx.lifecycle.ViewModelProvider
import by.dzmitrey.danilau.muzhelpermessenger.di.ViewModelKey
import by.dzmitrey.danilau.muzhelpermessenger.account.registration.presentation.RegistrationViewModel
import by.dzmitrey.danilau.muzhelpermessenger.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelFactoryModule {

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    fun bindRegistrationViewModel(registrationViewModel: RegistrationViewModel): RegistrationViewModel

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}