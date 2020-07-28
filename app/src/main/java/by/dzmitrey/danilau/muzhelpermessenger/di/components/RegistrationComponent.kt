package by.dzmitrey.danilau.muzhelpermessenger.di.components

import by.dzmitrey.danilau.muzhelpermessenger.authenticating.AuthActivity
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.login.LoginFragment
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.registration.RegistrationFragment
import by.dzmitrey.danilau.muzhelpermessenger.di.modules.AuthModule
import dagger.Subcomponent

@Subcomponent(modules = [AuthModule::class])
interface RegistrationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RegistrationComponent
    }

    fun inject(activity: AuthActivity)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: RegistrationFragment)
}