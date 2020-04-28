package by.dzmitrey.danilau.muzhelpermessenger.di.components

import by.dzmitrey.danilau.muzhelpermessenger.di.modules.AppSubComponentsModule
import by.dzmitrey.danilau.muzhelpermessenger.di.modules.ApplicationModule
import by.dzmitrey.danilau.muzhelpermessenger.di.modules.ViewModelFactoryModule
import by.dzmitrey.danilau.muzhelpermessenger.registration.presentation.RegistrationActivity
import by.dzmitrey.danilau.muzhelpermessenger.registration.presentation.RegistrationFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ViewModelFactoryModule::class,
        AppSubComponentsModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fragment: RegistrationFragment)

}
