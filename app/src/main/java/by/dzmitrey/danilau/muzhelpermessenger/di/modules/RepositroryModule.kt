package by.dzmitrey.danilau.muzhelpermessenger.di.modules

import by.dzmitrey.danilau.muzhelpermessenger.account.registration.data.RegistrationRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
@Singleton
interface RepositroryModule {

    @Binds
    fun provideRegisrationRepository(): RegistrationRepository

}