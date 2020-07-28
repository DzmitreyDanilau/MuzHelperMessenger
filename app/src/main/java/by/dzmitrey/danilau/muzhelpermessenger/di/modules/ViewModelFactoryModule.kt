package by.dzmitrey.danilau.muzhelpermessenger.di.modules

import androidx.lifecycle.ViewModelProvider
import by.dzmitrey.danilau.muzhelpermessenger.utils.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {



    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}